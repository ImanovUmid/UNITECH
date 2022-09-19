package com.register.registerapi;


import com.register.registerapi.entity.UserEntity;
import com.register.registerapi.repository.RegisterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RegisterRepositoryTest {

    @Autowired
    private RegisterRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenFindUserByPinIsExisting() {
        String pin = "7chh3zg";
        UserEntity userEntity = new UserEntity(
                "Seygey Seygey",
                pin,
                "123456"
        );
        underTest.save(userEntity);

        Optional<UserEntity> exists = underTest.findUserByPin(pin);

        assertThat(exists).isPresent();
    }
    @Test
    void itShouldCheckWhenFindUserByPinIsNotExisting() {

        String pin = "7chh3zg";

        Optional<UserEntity> exists = underTest.findUserByPin(pin);

        assertThat(exists).isNotPresent();
    }
}