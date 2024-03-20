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
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String userName;
    private String firstName;
    @OneToMany(mappedBy = "users")
    private List<BankAccountTransaction> bankAccountTransactionList;
    public Users(String userName) {
        this.userName = userName;
    }
}
