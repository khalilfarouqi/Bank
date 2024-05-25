package com.bank.bankservice.controller;

import com.bank.bankservice.common.dtos.Request.UpdatePasswordRequest;
import com.bank.bankservice.common.dtos.Response.UpdatePasswordResponse;
import com.bank.bankservice.services.Implimentation.UserServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "*")
public class UserGraphqlController {
    private final UserServiceImpl userService;
    public UserGraphqlController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @MutationMapping
    public UpdatePasswordResponse updatePassword(@Argument("dto") UpdatePasswordRequest updatePasswordRequest) {
        return userService.updatePassword(updatePasswordRequest);
    }
}
