package com.bank.bankservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends Users {
    private Date dateOfBirth;
    private String email;
    private String address;

    @Column(unique = true)
    private String identityRef;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccounts;
}
