package com.Currency.currencyrateapi.currency;

import com.Currency.currencyrateapi.currency.entity.CurrencyRateEntity;
import com.Currency.currencyrateapi.currency.entity.LogsCurrencyEntity;
import com.Currency.currencyrateapi.currency.entity.UserEntity;
import com.Currency.currencyrateapi.currency.repository.CurrencyRateRepository;
import com.Currency.currencyrateapi.currency.repository.LogsCurrencyRepository;
import com.Currency.currencyrateapi.currency.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private LogsCurrencyRepository logsCurrencyRepository;
    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private CurrencyService currencyService;

    String val1;
    String val2;
    Long check;
    CurrencyRateEntity currReturnValue1;
    CurrencyRateEntity currReturnValue2;
    LogsCurrencyEntity logsCurrencyEntity;

    Date maxDate;

    @BeforeEach
    void setUp() {
        val1 = "USD";
        val2 = "AZN";
        check = 0l;

        maxDate = new Date();

        currReturnValue1 = new CurrencyRateEntity();
        currReturnValue1.setId(1l);
        currReturnValue1.setCbarCurr(1.340d);
        currReturnValue1.setCurrencyCode("EUR");
        currReturnValue1.setCurrencyName("Dollar");

        currReturnValue2 = new CurrencyRateEntity();
        currReturnValue2.setId(2l);
        currReturnValue2.setCbarCurr(2d);
        currReturnValue2.setCurrencyCode("AZN");
        currReturnValue2.setCurrencyName("Manat");

        UserEntity user = new UserEntity();
        user.setFullname("Umid Imanov");
        user.setPin("1234567");


        logsCurrencyEntity = new LogsCurrencyEntity();
        logsCurrencyEntity.setCurrency("USD");
        logsCurrencyEntity.setCheckDate(maxDate);
        logsCurrencyEntity.setUserId(user);
    }

    @Test
    void givenCurrencyRequestReturnsResult() throws ParserConfigurationException, IOException, SAXException {
        when(currencyRateRepository.findCurrCheck()).thenReturn(check);

        when(currencyRateRepository.findCurrencyValueBy1(anyString())).thenReturn(currReturnValue1);
        when(currencyRateRepository.findCurrencyValueBy2(anyString())).thenReturn(currReturnValue2);

        when(logsCurrencyRepository.findLastDate(anyString())).thenReturn(maxDate);
        when(logsCurrencyRepository.save(any(LogsCurrencyEntity.class))).thenReturn(logsCurrencyEntity);

        Double result = currencyService.checkCurrency(val1, val2);

        assertThat(result).isEqualTo(currReturnValue1.getCbarCurr()/currReturnValue2.getCbarCurr());
    }

    @Test
    void updateCurrencyReturnsSuccess() throws ParserConfigurationException, IOException, SAXException {
        when(currencyRateRepository.findCurrencyValueBy1(anyString())).thenReturn(currReturnValue2);

        String result = currencyService.updateCurrency();

        assertThat(result).isEqualTo("Success");

    }
}