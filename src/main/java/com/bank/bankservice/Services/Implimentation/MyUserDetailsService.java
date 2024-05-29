package com.bank.bankservice.services.Implimentation;

import com.bank.bankservice.entity.Users;
import com.bank.bankservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Users foundUser = user.get();
        GrantedAuthority authority = new SimpleGrantedAuthority(foundUser.getProfile().name());

        return new org.springframework.security.core.userdetails.User(
                foundUser.getUserName(),
                foundUser.getPassword(),
                Collections.singleton(authority)
        );
    }
}
