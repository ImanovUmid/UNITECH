package com.login.login.repository;

import com.login.login.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginEntityRepository extends JpaRepository<LoginEntity, Long> {

}
