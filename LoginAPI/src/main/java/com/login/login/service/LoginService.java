package com.login.login.service;

import com.login.login.entity.UserEntity;
import com.login.login.entity.LoginEntity;
import com.login.login.repository.LoginEntityRepository;
import com.login.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginEntityRepository loginEntityRepository;
    private LoginEntity loginEntity;

    @Autowired
    public LoginService(LoginRepository loginRepository, LoginEntityRepository loginEntityRepository) {
        this.loginRepository = loginRepository;
        this.loginEntityRepository = loginEntityRepository;
    }

    public LoginEntity getLoginCheck() {
        return loginEntity;
    }

    public void setLoginCheck(LoginEntity loginEntity) {
        this.loginEntity = loginEntity;
    }

    public String login(UserEntity usr) {
        UUID randomUUID = UUID.randomUUID();
        String randomStr = randomUUID.toString().replaceAll("_", "");
        UserEntity userEntity = loginRepository.findUser(usr.getPin(), usr.getPassword());
        if (userEntity != null) {
            loginEntity = new LoginEntity();
            loginEntity.setPin(userEntity.getPin());
            loginEntity.setToken(randomStr);
            loginEntityRepository.save(loginEntity);
            return randomStr;
        } else {
            throw new IllegalStateException("Login Failed: Your user PIN or password is incorrect");

        }
    }
}
