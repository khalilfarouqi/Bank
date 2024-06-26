package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.common.dtos.Request.AddUserRequest;
import com.bank.bankservice.common.dtos.Request.UpdatePasswordRequest;
import com.bank.bankservice.common.dtos.Response.AddUserResponse;
import com.bank.bankservice.common.dtos.Response.UpdatePasswordResponse;
import com.bank.bankservice.common.dtos.UsersDto;
import com.bank.bankservice.entity.Users;
import com.bank.bankservice.entity.enums.Profile;
import com.bank.bankservice.exception.BusinessException;
import com.bank.bankservice.repository.UserRepository;
import com.bank.bankservice.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private ModelMapper modelMapper;

    @Override
    public List<UsersDto> getAll() {
        return userRepository.findAll()
                        .stream()
                        .map(users -> modelMapper.map(users, UsersDto.class))
                        .toList();
    }

    @Override
    public UsersDto getById(Long id) {
        return null;
    }

    @Override
    public UsersDto save(UsersDto dto) {
        return null;
    }

    @Override
    public UsersDto update(UsersDto dto) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }

    @Override
    public Optional<Users> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();
        Optional<Users> optionalUser = userRepository.findByUserName(updatePasswordRequest.getUsername());
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            if (encoder.matches(updatePasswordRequest.getActPassword(), user.getPassword())) {
                if (!updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getNewPasswordConfirm()))
                    throw new BusinessException("Les nouveaux mots de passe ne correspondent pas.");
                user.setPassword(encoder.encode(updatePasswordRequest.getNewPassword()));
                update(modelMapper.map(user, UsersDto.class));
                updatePasswordResponse.setMessage("Password updated successfully");
            } else
                throw new BusinessException("Mot de passe actuel incorrect");
        } else {
            throw new BusinessException("Utilisateur non trouvé");
        }
        return updatePasswordResponse;
    }

    @Override
    public AddUserResponse signUpUser(AddUserRequest addUserRequest) {
        Users user = modelMapper.map(addUserRequest, Users.class);
        String username = user.getUserName();
        user.setProfile(Profile.AGENT_GUICHET);
        user.setPassword(encoder.encode(addUserRequest.getPassword()));
        userRepository.findByUserName(username)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("The username [%s] is already used", username));
                });
        return modelMapper.map(userRepository.save(user), AddUserResponse.class);
    }
}
