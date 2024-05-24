package com.bank.bankservice.controller;

import com.bank.bankservice.common.CommonTools;
import com.bank.bankservice.common.dtos.Request.AddWirerTransferRequest;
import com.bank.bankservice.common.dtos.Request.GetTransactionListRequest;
import com.bank.bankservice.common.dtos.Response.AddWirerTransferResponse;
import com.bank.bankservice.common.dtos.TransactionDto;
import com.bank.bankservice.services.Implimentation.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionGraphqlController {
    private TransactionServiceImpl transactionServiceImpl;
    private CommonTools commonTools;
    @MutationMapping
    public AddWirerTransferResponse addWirerTransfer(@Argument("dto") AddWirerTransferRequest dto) {
        return transactionServiceImpl.wiredTransfer(dto);
    }
    @QueryMapping
    public List<TransactionDto> getTransactions(@Argument GetTransactionListRequest dto) {
        return transactionServiceImpl.getTransactions(dto);
    }
    @QueryMapping
    public List<TransactionDto> getTop10TransactionsById(@Argument Long id) {
        return transactionServiceImpl.getTop10TransactionsById(id);
    }
}
