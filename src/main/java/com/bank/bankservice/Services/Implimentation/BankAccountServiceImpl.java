package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.repository.BankAccountRepository;
import com.bank.bankservice.repository.CustomerRepository;
import com.bank.bankservice.services.IBankAccountService;
import com.bank.bankservice.common.dtos.BankAccountDto;
import com.bank.bankservice.common.dtos.Request.AddBankAccountRequest;
import com.bank.bankservice.common.dtos.Response.AddBankAccountResponse;
import com.bank.bankservice.entity.BankAccount;
import com.bank.bankservice.entity.Customer;
import com.bank.bankservice.entity.enums.AccountStatus;
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
public class BankAccountServiceImpl implements IBankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    @Override
    public AddBankAccountResponse saveBankAccount(AddBankAccountRequest dto) {
        BankAccount bankAccount = modelMapper.map(dto, BankAccount.class);
        Customer customerP = customerRepository.findByIdentityRef(bankAccount.getCustomer().getIdentityRef()).orElseThrow(
                        () -> new BusinessException(String.format("No customer with the identity: %s exist", dto.getCustomerIdentityRef())));
        bankAccount.setAccountStatus(AccountStatus.OPENED);
        bankAccount.setCustomer(customerP);
        bankAccount.setAmount((double) 0);
        bankAccount.setCreatedAt(new Date());
        AddBankAccountResponse response = modelMapper.map(bankAccountRepository.save(bankAccount), AddBankAccountResponse.class);
        response.setMessage(String.format("RIB number [%s] for the customer [%s] has been successfully created", dto.getRib(), dto.getCustomerIdentityRef()));
        return response;
    }

    @Override
    public List<BankAccountDto> getAllBankAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(bankAccount -> modelMapper.map(bankAccount, BankAccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDto getBankAccountByRib(String rib) {
        return modelMapper.map(bankAccountRepository.findByRib(rib).orElseThrow(
                () -> new BusinessException(String.format("No Bank Account with rib [%s] exist", rib))), BankAccountDto.class);
    }

    public List<BankAccountDto> findByCustomerId(Long id) {
        return bankAccountRepository.findByCustomerId(id).stream()
                .map(bankAccount -> modelMapper.map(bankAccount, BankAccountDto.class))
                .collect(Collectors.toList());
    }

    public List<BankAccountDto> findByCustomerUserName(String username) {
        return bankAccountRepository.findByCustomerUserName(username).stream()
                .map(bankAccount -> modelMapper.map(bankAccount, BankAccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BankAccountDto> getAll() {
        return null;
    }

    @Override
    public BankAccountDto getById(Long id) {
        return null;
    }

    @Override
    public BankAccountDto save(BankAccountDto dto) {
        return null;
    }

    @Override
    public BankAccountDto update(BankAccountDto dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return null;
    }
}
