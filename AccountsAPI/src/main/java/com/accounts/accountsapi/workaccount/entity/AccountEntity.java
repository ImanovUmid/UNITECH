package com.accounts.accountsapi.workaccount.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class AccountEntity {
    @Id
    @SequenceGenerator(
            name = "seq_acc",
            sequenceName = "seq_acc",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_acc"
    )

    private Long id;
    private String accNumber;
    private Integer balanceNumber;
    private Date openDate;
    private Date closeDate;
    private String accName;
    private String valName;
    private String filial;
    private Integer limit;
    private Double balance;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userId;

    public AccountEntity() {

    }

    public AccountEntity(Long id,
                         String accNumber,
                         Integer balanceNumber,
                         Date openDate,
                         Date closeDate,
                         String accName,
                         String valName,
                         String filial,
                         Integer limit,
                         Double balance) {
        this.id = id;
        this.accNumber = accNumber;
        this.balanceNumber = balanceNumber;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.accName = accName;
        this.valName = valName;
        this.filial = filial;
        this.limit = limit;
        this.balance = balance;

    }

    public AccountEntity(
            String accNumber,
            Integer balanceNumber,
            Date openDate,
            Date closeDate,
            String accName,
            String valName,
            String filial,
            Integer limit,
            Double balance) {
        this.accNumber = accNumber;
        this.balanceNumber = balanceNumber;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.accName = accName;
        this.valName = valName;
        this.filial = filial;
        this.limit = limit;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public Integer getBalanceNumber() {
        return balanceNumber;
    }

    public void setBalanceNumber(Integer balanceNumber) {
        this.balanceNumber = balanceNumber;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", accNumber='" + accNumber + '\'' +
                ", balanceNumber=" + balanceNumber +
                ", openDate='" + openDate + '\'' +
                ", closeDate=" + closeDate +
                ", accName='" + accName + '\'' +
                ", valName=" + valName +
                ", balance='" + balance + '\'' +
                ", filial=" + filial +
                ", limit='" + limit + '\'' +
                '}';
    }
}
