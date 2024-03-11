package com.bank.bankservice.common.dtos.Response;

import com.bank.bankservice.common.dtos.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddWirerTransferResponse {
    private String message;
    private TransactionDto transactionFrom;
    private TransactionDto transactionTo;
}
