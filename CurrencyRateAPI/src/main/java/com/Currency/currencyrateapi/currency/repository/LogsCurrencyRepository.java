package com.Currency.currencyrateapi.currency.repository;

import com.Currency.currencyrateapi.currency.entity.LogsCurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LogsCurrencyRepository extends JpaRepository<LogsCurrencyEntity, Long> {
    @Query("select max(t.checkDate) from LogsCurrencyEntity t where t.currency =?1")
    Date findLastDate(String val1);
}
