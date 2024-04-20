package com.example.accountdbauthen.repo;

import com.example.accountdbauthen.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByUsername(String username);
}
