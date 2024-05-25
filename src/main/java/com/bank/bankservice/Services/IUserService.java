package com.bank.bankservice.services;

import com.bank.bankservice.common.dtos.Request.UpdatePasswordRequest;
import com.bank.bankservice.common.dtos.Response.UpdatePasswordResponse;
import com.bank.bankservice.common.dtos.UsersDto;
import com.bank.bankservice.entity.Users;

import java.util.Optional;

public interface IUserService extends IService<UsersDto> {
    Optional<Users> findByUserName(String username);
    UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest);
}
