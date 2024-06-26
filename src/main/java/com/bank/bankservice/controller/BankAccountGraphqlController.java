package com.bank.bankservice.controller;

import com.bank.bankservice.common.dtos.BankAccountDto;
import com.bank.bankservice.common.dtos.Request.AddBankAccountRequest;
import com.bank.bankservice.common.dtos.Response.AddBankAccountResponse;
import com.bank.bankservice.services.Implimentation.BankAccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BankAccountGraphqlController {
    private final BankAccountServiceImpl bankAccountServiceImpl;
    @QueryMapping
    List<BankAccountDto> bankAccounts() {
        return bankAccountServiceImpl.getAllBankAccounts();
    }
    @QueryMapping
    BankAccountDto bankAccountByRib(@Argument String rib) {
        return bankAccountServiceImpl.getBankAccountByRib(rib);
    }
    @QueryMapping
    List<BankAccountDto> bankAccountById(@Argument Long id) {
        return bankAccountServiceImpl.findByCustomerId(id);
    }
    @QueryMapping
    List<BankAccountDto> bankAccountByUsername(@Argument String username) {
        return bankAccountServiceImpl.findByCustomerUserName(username);
    }
    @MutationMapping
    public AddBankAccountResponse addBankAccount(@Argument("dto") AddBankAccountRequest dto) {
        return bankAccountServiceImpl.saveBankAccount(dto);
    }
}
