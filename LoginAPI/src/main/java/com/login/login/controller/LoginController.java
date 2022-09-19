package com.login.login.controller;


import com.login.login.service.LoginService;
import com.login.login.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "UniTech/LoginAPI/login")
public class LoginController {


    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody UserEntity userEntity){
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(userEntity));
    }


}
