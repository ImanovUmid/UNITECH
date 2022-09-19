package com.register.registerapi.controller;

import com.register.registerapi.entity.UserEntity;
import com.register.registerapi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "UniTech/RegisterApi/registration")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public void registerNewUser(@RequestBody UserEntity user){
        registerService.addNewUser(user);
    }
}