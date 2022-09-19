package com.login.login.repository;

import com.login.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {

    @Query("select t from UserEntity t where t.pin = ?1 and t.password = ?2 ")
    UserEntity findUser(String pin, String password);
}
