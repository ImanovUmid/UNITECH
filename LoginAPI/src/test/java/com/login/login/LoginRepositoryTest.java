package com.login.login;

import com.login.login.entity.UserEntity;
import com.login.login.repository.LoginRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class LoginRepositoryTest {

    @Autowired
    private LoginRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenFindUserByPinAndPasswordIsExisting() {
        String pin = "7chh3zg";
        String pass = "123456";
        UserEntity userEntity = new UserEntity(
                "Seygey Seygey",
                pin,
                pass
        );
        underTest.save(userEntity);

        UserEntity exists = underTest.findUser(pin,pass);

        assertThat(exists).isEqualTo(userEntity);
    }

    @Test
    void itShouldCheckWhenFindUserByPinAndPasswordIsNotExisting() {
        String pin = "7chh3zg";
        String pass = "123456";

        UserEntity exists = underTest.findUser(pin,pass);

        assertThat(exists).isNull();
    }

}