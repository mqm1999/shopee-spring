package com.example.codese_spring.service;

import com.example.codese_spring.constant.Constant;
import com.example.codese_spring.dto.AccountDTO;
import com.example.codese_spring.entity.Account;
import com.example.codese_spring.exception.AccountExistedException;
import com.example.codese_spring.helper.Jwt.Jwt;
import com.example.codese_spring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Boolean addAccount(AccountDTO accountDTO) throws AccountExistedException {
        if (accountRepository.getAccountByName(accountDTO.getDisplay()) == null) {
            if (accountRepository.addAccount(accountDTO) != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new AccountExistedException("Account existed");
        }
    }

    public AccountDTO getAccountInfo(String token) throws Exception {
        String userId = Jwt.verifyToken(token, Constant.SECRET_KEY);
        List<AccountDTO> listResponse = accountRepository.getAccountById(userId);
        if(listResponse.size() > 0) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountID(listResponse.get(0).getAccountID());
            accountDTO.setAvatar(listResponse.get(0).getAvatar());
            accountDTO.setDisplay(listResponse.get(0).getDisplay());
            accountDTO.setEmail(listResponse.get(0).getEmail());
            return accountDTO;
        } else {
            throw new Exception("Account not found");
        }
    }
}
