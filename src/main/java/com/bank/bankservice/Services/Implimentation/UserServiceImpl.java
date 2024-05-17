package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.common.dtos.UsersDto;
import com.bank.bankservice.entity.Users;
import com.bank.bankservice.repository.UserRepository;
import com.bank.bankservice.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService, UserDetailsService {
    private UserRepository userRepository;
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

    public Optional<Users> findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));

    }

    private Set<SimpleGrantedAuthority> getAuthority(Users user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getProfile().name()));
        return authorities;
    }
}
