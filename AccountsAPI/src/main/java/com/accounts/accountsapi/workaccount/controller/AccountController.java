package com.accounts.accountsapi.workaccount.controller;


import com.accounts.accountsapi.workaccount.entity.AccountEntity;
import com.accounts.accountsapi.workaccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "UniTech/AccountsAPI/getAcc")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/addAcc/{pin}")
    public ResponseEntity<String> registerNewAccount(@RequestBody AccountEntity accountEntity, @PathVariable String pin) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.addAccounts(accountEntity, pin));
    }

    @GetMapping(value = "/showAcc/{token}")
    public ResponseEntity<Optional<List<AccountEntity>>> getAccounts(@PathVariable String token) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.showAccounts(token));
    }

}
