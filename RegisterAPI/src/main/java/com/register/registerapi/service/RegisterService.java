package com.register.registerapi.service;

import com.register.registerapi.repository.RegisterRepository;
import com.register.registerapi.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterService {
    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public void addNewUser(UserEntity user){
        Optional<UserEntity> usersOptional = registerRepository.findUserByPin(user.getPin());
        if (usersOptional.isPresent()){
            throw new IllegalStateException("Already registered pin");
        }
        registerRepository.save(user);
    }
}
