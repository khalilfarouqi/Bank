package com.bank.bankservice.common.dtos.Response;

import com.bank.bankservice.common.dtos.CustomerDto;
import com.bank.bankservice.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddBankAccountResponse {
    private Long id;
    private String message;
    private String rib;
    private Double amount;
    private String createdAt;
    private AccountStatus accountStatus;
    private CustomerDto customer;
}
