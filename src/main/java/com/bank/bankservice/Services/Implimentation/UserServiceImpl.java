package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.common.dtos.UsersDto;
import com.bank.bankservice.entity.Users;
import com.bank.bankservice.repository.UserRepository;
import com.bank.bankservice.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
        return List.of();
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
        return Optional.empty();
    }
}
