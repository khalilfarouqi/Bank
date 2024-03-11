package com.bank.bankservice.common.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddCustomerResponse {
    private Long id;
    private String message;
    private String userName;
    private String identifyRef;
    private String firstName;
    private String lastName;
}
