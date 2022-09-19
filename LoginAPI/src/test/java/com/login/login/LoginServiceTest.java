package com.login.login;

import com.login.login.entity.LoginEntity;
import com.login.login.entity.UserEntity;
import com.login.login.repository.LoginEntityRepository;
import com.login.login.repository.LoginRepository;
import com.login.login.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;
    @Mock
    private LoginEntityRepository loginEntityRepository;
    @InjectMocks
    private LoginService underTest;

    UserEntity userEntity;
    LoginEntity loginEntity;

    @BeforeEach
    void setUp() {
         userEntity = new UserEntity(
                "Rustam Alekber",
                "186hrmh",
                "123456"
        );


        underTest = new LoginService(loginRepository, loginEntityRepository);
    }

    @Test
    void whenLoginRequestIsReturnToken() {
        //Arrange
        when(loginRepository.findUser(userEntity.getPin(), userEntity.getPassword())).thenReturn(userEntity);


       //Act
        String returnS =  underTest.login(userEntity);
        System.out.println(returnS);

        assertThat(returnS).isNotNull();
    }
    @Test
    void willThrowWhenUserCannotLogin() {
        //Arrange
        when(loginRepository.findUser(userEntity.getPin(), userEntity.getPassword())).thenReturn(null);
        //Act
        assertThatThrownBy(() -> underTest.login(userEntity))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Your user PIN or password is incorrect");
    }
}