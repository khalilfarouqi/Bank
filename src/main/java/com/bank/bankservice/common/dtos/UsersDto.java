package com.bank.bankservice.common.dtos;

import com.bank.bankservice.entity.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsersDto {
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected Profile profile;
}
