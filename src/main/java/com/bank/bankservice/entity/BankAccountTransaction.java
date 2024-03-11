package com.bank.bankservice.entity;

import com.bank.bankservice.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankAccountTransaction {
    @Id
    @GeneratedValue
    private Long id;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    private BankAccount bankAccount;
    @ManyToOne
    private User user;
}
