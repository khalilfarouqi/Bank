package com.bank.bankservice;

import com.bank.bankservice.jwt.TokenManager;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test2 {
    private final static long PERIOD_VALIDITY = 1 * 24 * 60 * 60 * 1000;
    public static void main(String[] args) {
        Map<String, Object> cles = new HashMap<>();
        Date dateCreation = new Date();
        Date dateExpiration = new Date(dateCreation.getTime() + PERIOD_VALIDITY);
        cles.put("sub", "admin");
        cles.put("roles", Arrays.asList("ADMIN", "CLIENT"));
        String token= TokenManager.generateTokenwithRoles(cles, dateCreation, dateExpiration);
        System.out.println(token);
    }
}
