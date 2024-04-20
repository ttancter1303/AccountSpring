package com.example.accountdbauthen.controller;

import com.example.accountdbauthen.entity.Account;
import com.example.accountdbauthen.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping()
    public ResponseEntity getAll(){
        List<Account> accounts = accountRepository.findAll();
        return new ResponseEntity<>("get all "+accounts, HttpStatus.OK);
    }
    public boolean doesUserExist(String username) {
        return accountRepository.findByUsername(username) != null;
    }
    @PostMapping("save")
    public ResponseEntity save (@RequestBody Account account){
        String password2 = new BCryptPasswordEncoder().encode(account.getPassword());
        account.setPassword(password2);
        if (doesUserExist(account.getUsername())){
            return new ResponseEntity<>("Account exist save fail",HttpStatus.BAD_REQUEST);
        }
        accountRepository.save(account);
        return new ResponseEntity<>("Save account",HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity update(@RequestBody Account account,@PathVariable Integer id){
        Account accountDB = accountRepository.findById(id).orElse(null);
        if (accountDB ==null){
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
        accountDB.setEmail(account.getEmail());
        accountDB.setUpdateAt(account.getUpdateAt());
        accountDB.setPassword(account.getPassword());
        accountDB.setFullName(account.getFullName());
        accountRepository.save(accountDB);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null){
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
        accountRepository.delete(account);
        return new ResponseEntity<>("Account deleted",HttpStatus.OK);

    }
}
