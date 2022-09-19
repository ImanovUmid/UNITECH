package com.register.registerapi;

import com.register.registerapi.entity.UserEntity;
import com.register.registerapi.repository.RegisterRepository;
import com.register.registerapi.service.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {
    @Mock
    private RegisterRepository registerRepository;
    private RegisterService underTest;


    @BeforeEach
    void setUp() {
        underTest = new RegisterService(registerRepository);
    }


    @Test
    void canAddNewUser() {
        UserEntity userEntity = new UserEntity(
                "Seygey Seygey",
                "7chh3zg",
                "123456"
        );

        underTest.addNewUser(userEntity);

        ArgumentCaptor<UserEntity> usersArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(registerRepository).save(usersArgumentCaptor.capture());
        UserEntity capturedUserEntity = usersArgumentCaptor.getValue();
        assertThat(capturedUserEntity).isEqualTo(userEntity);
    }
    @Test
    void willThrowWhenPinIsTaken() {
        UserEntity userEntity = new UserEntity(
                "Seygey Seygey",
                "7chh3zg",
                "123456"
        );
        given(registerRepository.findUserByPin(userEntity.getPin())).willReturn(Optional.of(userEntity));

        assertThatThrownBy(() -> underTest.addNewUser(userEntity))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Already registered pin");
        verify(registerRepository, never()).save(any());

    }
}