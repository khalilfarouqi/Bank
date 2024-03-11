package com.bank.bankservice.Repository;

import com.bank.bankservice.entity.BankAccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BankAccountTransactionRepository extends JpaRepository<BankAccountTransaction, Long> {
    List<BankAccountTransaction> findByBankAccount_RibAndCreatedAtBetween(String rib, Date from, Date to);
}
