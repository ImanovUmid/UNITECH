package com.accounts.acctoaccapi.acctoacc.repository;

import com.accounts.acctoaccapi.acctoacc.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("select t from AccountEntity t where t.accNumber = ?1")
    AccountEntity findAccountsByAccNumber(String number);


}