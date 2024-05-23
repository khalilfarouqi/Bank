package com.bank.bankservice.entity;

import com.bank.bankservice.entity.enums.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {
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
}
