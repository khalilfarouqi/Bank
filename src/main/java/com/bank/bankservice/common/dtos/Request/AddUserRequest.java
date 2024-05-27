package com.bank.bankservice.common.dtos.Request;

import com.bank.bankservice.entity.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddUserRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
