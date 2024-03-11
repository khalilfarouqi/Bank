package com.bank.bankservice.common.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddWirerTransferRequest {
    private String ribFrom;
    private String ribTo;
    private Double amount;
    private String userName;
}
