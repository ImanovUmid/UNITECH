package com.accounts.acctoaccapi.acctoacc.service;

import com.accounts.acctoaccapi.acctoacc.entity.AccountEntity;
import com.accounts.acctoaccapi.acctoacc.entity.ArchPaymentEntity;
import com.accounts.acctoaccapi.acctoacc.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.accounts.acctoaccapi.acctoacc.repository.ArchPaymentRepository;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class AccToAccService {
    private final AccountRepository accountRepository;
    private final ArchPaymentRepository archPaymentRepository;

    @Autowired
    public AccToAccService(AccountRepository accountRepository, ArchPaymentRepository archPaymentRepository) {
        this.accountRepository = accountRepository;
        this.archPaymentRepository = archPaymentRepository;
    }

    @Transactional
    public String moneyTransfer(Double amount, String sendAccNumber, String getAccNumber) {
        Date today = new Date();
        Double resultGetBalance = 0d;
        Double resultSendBalance = 0d;

        AccountEntity getAccount = accountRepository.findAccountsByAccNumber(getAccNumber);
        AccountEntity sendAccount = accountRepository.findAccountsByAccNumber(sendAccNumber);

        if (getAccount == null) {
            throw new IllegalStateException("There is to make transfer to non existing account (Acceptor)");
        } else if (sendAccount == null) {
            throw new IllegalStateException("There is to make transfer to non existing account (Sender)");
        } else {
            if (amount > getAccount.getBalance()) {
                throw new IllegalStateException("There is no enough money in my account balance");
            } else if (getAccNumber.equals(sendAccNumber)) {
                throw new IllegalStateException("There is try to make transfer to same account");
            } else if (getAccount.getCloseDate() != null) {
                throw new IllegalStateException("There is try to make transfer to deactive account (Acceptor)");
            } else if (sendAccount.getCloseDate() != null) {
                throw new IllegalStateException("There is try to make transfer to deactive account (Sender)");
            } else {

                resultGetBalance = getAccount.getBalance() - amount;
                resultSendBalance = sendAccount.getBalance() + amount;

                ArchPaymentEntity archPaymentEntity = new ArchPaymentEntity();
                archPaymentEntity.setPaymentDate(today);
                archPaymentEntity.setFromAccId(getAccount);
                archPaymentEntity.setToAccId(sendAccount);
                archPaymentEntity.setAmount(amount);
                archPaymentRepository.save(archPaymentEntity);

                getAccount.setBalance(resultGetBalance);

                sendAccount.setBalance(resultSendBalance);

                return "Success";
            }
        }
    }
}
