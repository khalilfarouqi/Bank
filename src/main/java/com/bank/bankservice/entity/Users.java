package com.bank.bankservice.entity;

import com.bank.bankservice.entity.enums.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Users implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Profile profile;
    @OneToMany(mappedBy = "users")
    private List<BankAccountTransaction> bankAccountTransactionList;
    public Users(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
