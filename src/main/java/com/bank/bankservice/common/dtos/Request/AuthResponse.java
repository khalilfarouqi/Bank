package com.bank.bankservice.common.dtos.Request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
