package com.accounts.accountsapi.workaccount.repository;

import com.accounts.accountsapi.workaccount.entity.AccountEntity;
import com.accounts.accountsapi.workaccount.entity.LoginEntity;
import com.accounts.accountsapi.workaccount.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("select t from UserEntity t where t.pin = ?1")
    Optional<UserEntity> findUserByPin(String pin);

    @Query("select t from LoginEntity t where t.token = ?1")
    Optional<LoginEntity> findLoginCheckByToken(String token);

    @Query("select t from AccountEntity t where t.userId.pin =?1 and t.closeDate is not null")
    Optional<List<AccountEntity>> findAccountsByDate(String pin);

}
