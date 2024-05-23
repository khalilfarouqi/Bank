package com.bank.bankservice.common.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String identityRef;
    private String address;
    private Date dateOfBirth;
    private String username;
}
