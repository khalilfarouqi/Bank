package com.bank.bankservice.repository;

import com.bank.bankservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByRib(String rib);
    List<BankAccount> findByCustomerId(Long id);
    List<BankAccount> findByCustomerUserName(String username);
}
