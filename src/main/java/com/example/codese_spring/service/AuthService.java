package com.example.codese_spring.service;

import com.example.codese_spring.dto.LoginSessionDTO;
import com.example.codese_spring.exception.AccountExistedException;
import com.example.codese_spring.exception.LoginFailException;
import com.example.codese_spring.repository.AccountRepository;
import com.example.codese_spring.repository.AuthRepository;
import com.example.codese_spring.security.AccountDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    @Autowired
    AccountRepository accountRepository;

    public LoginSessionDTO loginSession(String email, String password) throws LoginFailException {
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        List<AccountDTO> listResponse = authRepository.loginCheck(email, hashPassword);
        if (listResponse.size() > 0) {
            LoginSessionDTO loginSessionDTO = new LoginSessionDTO();
            String key = UUID.randomUUID().toString();
            String accountID = listResponse.get(0).getAccountID();
            loginSessionDTO.setKey(key);
            loginSessionDTO.setValue(accountID);
            authRepository.addToken(accountID, key);
            return loginSessionDTO;
        } else {
            throw new LoginFailException("Wrong email or password");
        }
    }

    public Boolean signUp(AccountDTO accountDTO) throws AccountExistedException {
        List<AccountDTO> listResponseEmail = accountRepository.getAccountInfoByEmail(accountDTO.getEmail());
        List<AccountDTO> listResponseDisplay = accountRepository.getAccountByName(accountDTO.getDisplay());
        if (listResponseEmail.size() == 0 && listResponseDisplay.size() == 0) {
            if (accountRepository.addAccount(accountDTO) != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new AccountExistedException("Account existed");
        }
    }


    public Boolean logoutSession(String token) throws Exception {
        if(authRepository.changeSessionStatus(token) != 0) {
            return true;
        } else {
            throw new Exception("Problem occurred");
        }
    }
}
