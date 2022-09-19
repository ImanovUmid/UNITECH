package com.Currency.currencyrateapi.currency.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class LogsCurrencyEntity {
    @Id
    @SequenceGenerator(
            name = "seq_currcheck",
            sequenceName = "seq_currcheck",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_currcheck"
    )
    private Long id;
    private String currency;
    private Date checkDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userId;

    public LogsCurrencyEntity() {

    }

    public LogsCurrencyEntity(Long id,
                              String currency,
                              Date checkDate) {
        this.id = id;
        this.currency = currency;
        this.checkDate = checkDate;
    }

    public LogsCurrencyEntity(String currency,
                              Date checkDate) {
        this.currency = currency;
        this.checkDate = checkDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LogsCurrencyEntity{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", checkDate=" + checkDate +
                '}';
    }
}
