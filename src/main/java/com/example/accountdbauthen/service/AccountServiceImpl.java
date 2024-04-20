package com.example.accountdbauthen.service;

import com.example.accountdbauthen.entity.Account;
import com.example.accountdbauthen.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: ");
        System.out.println(username);
        Account account = accountRepository.findByUsername(username);
        if (account==null){
            throw new UsernameNotFoundException("Not found account: "+username);
        }
        return new User(username,account.getPassword(), Collections.emptyList());
//        emtylist: tất cả đều có quyền như nhau chưa phân quyền
    }
}
