package com.bank.bankservice.services;

import com.bank.bankservice.common.dtos.BankAccountDto;
import com.bank.bankservice.common.dtos.Request.AddBankAccountRequest;
import com.bank.bankservice.common.dtos.Response.AddBankAccountResponse;

import java.util.List;

public interface IBankAccountService extends IService<BankAccountDto> {
    AddBankAccountResponse saveBankAccount(AddBankAccountRequest dto);
    List<BankAccountDto> getAllBankAccounts();
    BankAccountDto getBankAccountByRib(String rib);
}
