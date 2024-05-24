package com.bank.bankservice.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerDto {
    private Long id;
    private String identityRef;

    private Date dateOfBirth;
    private String email;
    private String address;

    private String username;
    private String firstName;
    private String lastName;
}
