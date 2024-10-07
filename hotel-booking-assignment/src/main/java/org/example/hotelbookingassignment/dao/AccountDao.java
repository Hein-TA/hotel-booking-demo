package org.example.hotelbookingassignment.dao;

import org.example.hotelbookingassignment.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByEmail(String email);
}
