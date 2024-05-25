package com.bank.bankservice.common.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdatePasswordRequest {
    private String username;
    private String actPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
