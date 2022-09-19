package com.Currency.currencyrateapi.currency.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class CurrencyRateEntity {
    @Id
    @SequenceGenerator(
            name = "seq_currrate",
            sequenceName = "seq_currrate",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_currrate"
    )
    private Long id;
    private String currencyName;
    private String currencyCode;
    private Date currencyDate;
    private Double cbarCurr;
    private Double buyCurr;
    private Double saleCurr;

    public CurrencyRateEntity() {

    }

    public CurrencyRateEntity(Long id,
                              String currencyName,
                              String currencyCode,
                              Date currencyDate,
                              Double cbarCurr,
                              Double buyCurr,
                              Double saleCurr) {
        this.id = id;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.currencyDate = currencyDate;
        this.cbarCurr = cbarCurr;
        this.buyCurr = buyCurr;
        this.saleCurr = saleCurr;
    }

    public CurrencyRateEntity(
            String currencyName,
            String currencyCode,
            Date currencyDate,
            Double cbarCurr,
            Double buyCurr,
            Double saleCurr) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.currencyDate = currencyDate;
        this.cbarCurr = cbarCurr;
        this.buyCurr = buyCurr;
        this.saleCurr = saleCurr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(Date currencyDate) {
        this.currencyDate = currencyDate;
    }

    public Double getCbarCurr() {
        return cbarCurr;
    }

    public void setCbarCurr(Double cbarCurr) {
        this.cbarCurr = cbarCurr;
    }

    public Double getBuyCurr() {
        return buyCurr;
    }

    public void setBuyCurr(Double buyCurr) {
        this.buyCurr = buyCurr;
    }

    public Double getSaleCurr() {
        return saleCurr;
    }

    public void setSaleCurr(Double saleCurr) {
        this.saleCurr = saleCurr;
    }

    @Override
    public String toString() {
        return "CurrencyRateEntity{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", currencyCode=" + currencyCode +
                ", currencyDate='" + currencyDate + '\'' +
                ", cbarCurr=" + cbarCurr +
                ", buyCurr='" + buyCurr + '\'' +
                ", saleCurr=" + saleCurr +
                '}';
    }

}

