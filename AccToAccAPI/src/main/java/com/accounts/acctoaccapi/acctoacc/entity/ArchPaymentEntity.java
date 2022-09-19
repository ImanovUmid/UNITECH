package com.accounts.acctoaccapi.acctoacc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ArchPaymentEntity {
    @Id
    @SequenceGenerator(
            name = "seq_arch_payment",
            sequenceName = "seq_arch_payment",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_arch_payment"
    )

    private Long id;
    private Date paymentDate;
    private Double amount;
    @JoinColumn(name = "FROM_ACC_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity fromAccId;

    @JoinColumn(name = "TO_ACC_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity toAccId;

    public ArchPaymentEntity() {

    }

    public ArchPaymentEntity(Long id,
                             Date paymentDate,
                             Double credit,
                             Double debit) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public ArchPaymentEntity(
            Date paymentDate,
            Double credit,
            Double debit) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public AccountEntity getFromAccId() {
        return fromAccId;
    }

    public void setFromAccId(AccountEntity fromAccId) {
        this.fromAccId = fromAccId;
    }

    public AccountEntity getToAccId() {
        return toAccId;
    }

    public void setToAccId(AccountEntity toAccId) {
        this.toAccId = toAccId;
    }

    @Override
    public String toString() {
        return "ArchPaymentEntity{" +
                "id=" + id +
                ", paymentDate='" + paymentDate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
