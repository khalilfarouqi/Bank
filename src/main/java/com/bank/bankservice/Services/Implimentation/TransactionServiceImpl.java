package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.repository.*;
import com.bank.bankservice.services.ITransactionService;
import com.bank.bankservice.common.dtos.Request.*;
import com.bank.bankservice.common.dtos.Response.*;
import com.bank.bankservice.common.dtos.TransactionDto;
import com.bank.bankservice.entity.BankAccount;
import com.bank.bankservice.entity.BankAccountTransaction;
import com.bank.bankservice.entity.GetTransactionListBo;
import com.bank.bankservice.entity.Users;
import com.bank.bankservice.entity.enums.AccountStatus;
import com.bank.bankservice.entity.enums.TransactionType;
import com.bank.bankservice.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountTransactionRepository bankAccountTransactionRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public List<TransactionDto> getAll() {
        return null;
    }

    @Override
    public TransactionDto getById(Long id) {
        return null;
    }

    @Override
    public TransactionDto save(TransactionDto dto) {
        return null;
    }

    @Override
    public TransactionDto update(TransactionDto dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }

    @Override
    public AddWirerTransferResponse wiredTransfer(AddWirerTransferRequest dto) {
        BankAccountTransaction transactionFrom = BankAccountTransaction.builder().
                amount(dto.getAmount()).
                transactionType(TransactionType.DEBIT).
                bankAccount(BankAccount.builder().rib(dto.getRibFrom()).build()).
                users(new Users(dto.getUserName())).
                build();
        BankAccountTransaction transactionTo = BankAccountTransaction.builder().
                amount(dto.getAmount()).
                transactionType(TransactionType.CREDIT).
                bankAccount(BankAccount.builder().rib(dto.getRibTo()).build()).
                users(new Users(dto.getUserName())).
                build();
        String username = transactionFrom.getUsers().getUserName();
        String ribFrom = transactionFrom.getBankAccount().getRib();
        String ribTo = transactionTo.getBankAccount().getRib();
        Double amount = transactionFrom.getAmount();
        Users users = userRepository.findByUserName(username).
                orElseThrow(() -> new BusinessException(String.format("User [%s] doesn't exist", username)));
        BankAccount bankAccountFrom = bankAccountRepository.findByRib(ribFrom).
                orElseThrow(() -> new BusinessException(String.format("No bank account have the rib %s", ribFrom)));
        BankAccount bankAccountTo = bankAccountRepository.findByRib(ribTo).
                orElseThrow(() -> new BusinessException(String.format("No bank account have the rib %s", ribTo)));
        checkBusinessRules(bankAccountFrom, bankAccountTo, amount);
//On débite le compte demandeur
        bankAccountFrom.setAmount(bankAccountFrom.getAmount() - amount);
//On crédite le compte destinataire
        bankAccountTo.setAmount(bankAccountTo.getAmount() + amount);
        transactionFrom.setCreatedAt(new Date());
        transactionFrom.setUsers(users);
        transactionFrom.setBankAccount(bankAccountFrom);
        transactionTo.setCreatedAt(new Date());
        transactionTo.setUsers(users);
        transactionTo.setBankAccount(bankAccountTo);
        bankAccountTransactionRepository.save(transactionFrom);
        bankAccountTransactionRepository.save(transactionTo);
        return AddWirerTransferResponse.builder().
                message(String.format("the transfer of an amount of %s from the %s bank account to %s was carried out successfully",
                        dto.getAmount(), dto.getRibFrom(), dto.getRibTo())).
                transactionFrom(modelMapper.map(transactionFrom, TransactionDto.class)).
                transactionTo(modelMapper.map(transactionTo, TransactionDto.class)).
                build();
    }
    private void checkBusinessRules(BankAccount bankAccountFrom, BankAccount bankAccountTo, Double amount){
        if (bankAccountFrom.getAccountStatus().equals(AccountStatus.CLOSED))
            throw new BusinessException(String.format("the bank account %s is closed !!", bankAccountFrom.getRib()));
        if (bankAccountFrom.getAccountStatus().equals(AccountStatus.BLOCKED))
            throw new BusinessException(String.format("the bank account %s is blocked !!", bankAccountFrom.getRib()));
        if (bankAccountTo.getAccountStatus().equals(AccountStatus.CLOSED))
            throw new BusinessException(String.format("the bank account %s is closed !!", bankAccountTo.getRib()));
        if (bankAccountTo.getAccountStatus().equals(AccountStatus.BLOCKED))
            throw new BusinessException(String.format("the bank account %s is blocked !!", bankAccountTo.getRib()));
        if (bankAccountFrom.getAmount() < amount)
            throw new BusinessException(String.format("the balance of account number %s is less than %s",
                    bankAccountFrom.getRib(), amount));
    }

    @Override
    public List<TransactionDto> getTransactions(GetTransactionListRequest dto) {
        GetTransactionListBo data = modelMapper.map(dto, GetTransactionListBo.class);
        return bankAccountTransactionRepository.findByBankAccount_RibAndCreatedAtBetween(data.getRib(), data.getDateFrom(), data.getDateTo())
                .stream()
                .map(bo ->
                        modelMapper.map(bo, TransactionDto.class)).collect(Collectors.toList());
    }

    public List<TransactionDto> getTop10TransactionsById(Long id) {
        return bankAccountTransactionRepository.getTop10BankAccountTransactionsByBankAccountIdOrderByCreatedAtDesc(id)
                .stream()
                .map(bo -> modelMapper.map(bo, TransactionDto.class)).collect(Collectors.toList());
    }
}
