package com.bank.bankservice.Services;

import com.bank.bankservice.common.dtos.Request.AddWirerTransferRequest;
import com.bank.bankservice.common.dtos.Request.GetTransactionListRequest;
import com.bank.bankservice.common.dtos.Response.AddWirerTransferResponse;
import com.bank.bankservice.common.dtos.TransactionDto;

import java.util.List;

public interface ITransactionService extends IService<TransactionDto> {
    AddWirerTransferResponse wiredTransfer(AddWirerTransferRequest dto);
    List<TransactionDto> getTransactions(GetTransactionListRequest dto);
}
