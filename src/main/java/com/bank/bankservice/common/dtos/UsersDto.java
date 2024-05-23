package com.bank.bankservice.common.dtos;

import com.bank.bankservice.entity.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsersDto {
    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    private Profile profile;
}
