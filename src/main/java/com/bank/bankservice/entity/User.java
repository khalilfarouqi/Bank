package com.bank.bankservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String userName;
    private String firstName;
    @OneToMany(mappedBy = "user")
    private List<BankAccountTransaction> bankAccountTransactionList;
    public User(String userName) {
        this.userName = userName;
    }
}
