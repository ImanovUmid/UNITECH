package com.accounts.acctoaccapi.acctoacc;

import com.accounts.acctoaccapi.acctoacc.entity.AccountEntity;
import com.accounts.acctoaccapi.acctoacc.entity.ArchPaymentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.accounts.acctoaccapi.acctoacc.repository.AccountRepository;
import com.accounts.acctoaccapi.acctoacc.repository.ArchPaymentRepository;
import com.accounts.acctoaccapi.acctoacc.service.AccToAccService;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccToAccServiceTest {


    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ArchPaymentRepository archPaymentRepository;

    @InjectMocks
    private AccToAccService accToAccService;

    @BeforeEach
    void setUp() {

        IBAN1 = "AZ46NABZ01350100000000015944";
        IBAN2 = "AZ46NABZ01350100000000015001";
        fromAcc = new AccountEntity(
                "AZ46NABZ01350100000000015944",
                41010,
                new Date(),
                null,
                "Müxbir Hesab",
                "AZN",
                "Baş ofis",
                10000,
                10000d);
        toAcc = new AccountEntity(
                "AZ46NABZ01350100000000015001",
                41010,
                new Date(),
                null,
                "Ikinci Hesab",
                "AZN",
                "Baş ofis",
                10000,
                3000d);


        archPayment = new ArchPaymentEntity();
        archPayment.setPaymentDate(new Date());
        archPayment.setAmount(100d);
        archPayment.setFromAccId(fromAcc);
        archPayment.setToAccId(toAcc);
    }

    ArchPaymentEntity archPayment;
    AccountEntity fromAcc;
    AccountEntity toAcc;
    String IBAN1;
    String IBAN2;


    @Test
    void moneyTransferBetweenTwoIBAN() {
        when(accountRepository.findAccountsByAccNumber(IBAN1)).thenReturn(fromAcc);
        when(accountRepository.findAccountsByAccNumber(IBAN2)).thenReturn(toAcc);
        when(archPaymentRepository.save(any(ArchPaymentEntity.class))).thenReturn(archPayment);

        String resp = accToAccService.moneyTransfer(100d, IBAN1, IBAN2);

        assertThat(resp).isEqualTo("Success");

    }
}