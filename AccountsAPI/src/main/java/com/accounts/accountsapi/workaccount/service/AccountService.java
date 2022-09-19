package com.accounts.accountsapi.workaccount.service;

import com.accounts.accountsapi.workaccount.repository.AccountRepository;
import com.accounts.accountsapi.workaccount.entity.AccountEntity;
import com.accounts.accountsapi.workaccount.entity.LoginEntity;
import com.accounts.accountsapi.workaccount.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String addAccounts(AccountEntity account, String pin) {
        Optional<UserEntity> usersOptional = accountRepository.findUserByPin(pin);
        if (usersOptional.isPresent()) {
            account.setUserId(usersOptional.get());
            accountRepository.save(account);
            return "Success";
        } else {
            throw new IllegalStateException("Your user PIN is incorrect");
        }
    }

    public Optional<List<AccountEntity>> showAccounts(String token) {
        Optional<LoginEntity> loginCheckOptional = accountRepository.findLoginCheckByToken(token);
        if (loginCheckOptional.isEmpty()) {
            throw new IllegalStateException("Your user Token is incorrect");
        }
        Optional<List<AccountEntity>> accountsList = accountRepository.findAccountsByDate(loginCheckOptional.get().getPin());
        return accountsList;
    }
}