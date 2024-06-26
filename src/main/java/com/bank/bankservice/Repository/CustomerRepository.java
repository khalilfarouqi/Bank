package com.bank.bankservice.repository;

import com.bank.bankservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentityRef(String identityRef);
    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByEmail(String email);
}
