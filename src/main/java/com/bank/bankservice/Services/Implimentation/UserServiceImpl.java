package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.common.dtos.Request.UpdatePasswordRequest;
import com.bank.bankservice.common.dtos.Response.UpdatePasswordResponse;
import com.bank.bankservice.common.dtos.UsersDto;
import com.bank.bankservice.entity.Users;
import com.bank.bankservice.exception.BusinessException;
import com.bank.bankservice.repository.UserRepository;
import com.bank.bankservice.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
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
            if (user.getPassword().equals(updatePasswordRequest.getActPassword())) {
                if (!updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getNewPasswordConfirm()))
                    throw new BusinessException("Les nouveaux mots de passe ne correspondent pas.");
                user.setPassword(updatePasswordRequest.getNewPassword());
                update(modelMapper.map(user, UsersDto.class));
                updatePasswordResponse.setMessage("Password updated successfully");
            } else
                throw new BusinessException("Mot de passe actuel incorrect");
        } else {
            throw new BusinessException("Utilisateur non trouvé");
        }
        return updatePasswordResponse;
    }

    public Optional<Users> findByPassword(String password) {
        return userRepository.findByPassword(password);
    }
}
