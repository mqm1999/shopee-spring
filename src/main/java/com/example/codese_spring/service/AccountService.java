package com.example.codese_spring.service;

import com.example.codese_spring.security.AccountDTO;
import com.example.codese_spring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Boolean addAccount(AccountDTO accountDTO) throws Exception {
        if (accountRepository.checkAccountExisted(accountDTO.getDisplay()) == 0) {
            if (accountRepository.addAccount(accountDTO) != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new Exception("Account existed");
        }
    }

}
