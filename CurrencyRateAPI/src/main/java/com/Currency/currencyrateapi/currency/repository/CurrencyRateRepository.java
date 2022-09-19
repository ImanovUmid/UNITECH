package com.Currency.currencyrateapi.currency.repository;

import com.Currency.currencyrateapi.currency.entity.CurrencyRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity, Long>{

    @Query("select count(t.id) from CurrencyRateEntity t ")
    Long findCurrCheck();
    @Query("select t from CurrencyRateEntity t where t.currencyCode = ?1")
    CurrencyRateEntity findCurrencyValueBy1(String val1);
    @Query("select t from CurrencyRateEntity t where t.currencyCode = ?1")
    CurrencyRateEntity findCurrencyValueBy2(String val2);

}
