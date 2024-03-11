package com.bank.bankservice.common.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateCustomerRequest {
    private String username;
    private String identifyRef;
    private String firstName;
    private String lastName;
}
