package com.accounts.accountsapi.workaccount;

import com.accounts.accountsapi.workaccount.entity.AccountEntity;
import com.accounts.accountsapi.workaccount.entity.LoginEntity;
import com.accounts.accountsapi.workaccount.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.accounts.accountsapi.workaccount.repository.AccountRepository;
import com.accounts.accountsapi.workaccount.service.AccountService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountEntityServiceTest {


    @Mock
    private AccountRepository accRepository;

    @InjectMocks
    private AccountService accService;

    AccountEntity account;
    UserEntity user;

    List<AccountEntity> listAccounts;
    LoginEntity loginEntity;
    String pin;
    @BeforeEach
    void setUp() {
        account = new AccountEntity(
                "AZ46NABZ01350100000000015944",
                41010,
                new Date(),
                null,
                "Müxbir Hesab",
                "AZN",
                "Baş ofis",
                10000,
                10000d);

        pin="186prmh";

        user = new UserEntity(
                "Ehmed Imanov",
                "186hrmh",
                "123456"
        );

    loginEntity = new LoginEntity();
    loginEntity.setPin("186hrmh");
    loginEntity.setToken("123456789");


    listAccounts = new ArrayList<>();
    listAccounts.add(account);
    }
    @Test
    void checkStatusIsSuccessWhileAddAccount() {

        when(accRepository.findUserByPin(pin)).thenReturn(Optional.of(user));

        accService.addAccounts(account,pin);

        ArgumentCaptor<AccountEntity> accArgumentCaptor = ArgumentCaptor.forClass(AccountEntity.class);
        verify(accRepository).save(accArgumentCaptor.capture());
        AccountEntity capturedAccount = accArgumentCaptor.getValue();
        assertThat(capturedAccount).isEqualTo(account);
    }

    @Test
    void givenTokenReturnListAccount() {
        when(accRepository.findLoginCheckByToken(loginEntity.getToken())).thenReturn(Optional.of(loginEntity));
        when(accRepository.findAccountsByDate(Optional.of(loginEntity).get().getPin())).thenReturn(Optional.of(listAccounts));

        Optional<List<AccountEntity>> respList=accService.showAccounts(loginEntity.getToken());
        assertThat(respList).isEqualTo(Optional.of(listAccounts));



    }
}