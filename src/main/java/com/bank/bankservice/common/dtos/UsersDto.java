package com.bank.bankservice.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsersDto {
    protected String userName;
    protected String firstName;
    protected String lastName;
}
