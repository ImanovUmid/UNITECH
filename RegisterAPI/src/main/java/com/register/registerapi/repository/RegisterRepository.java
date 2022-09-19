package com.register.registerapi.repository;
import com.register.registerapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<UserEntity, Long> {

    @Query("select t from UserEntity t where t.pin = ?1")
    Optional<UserEntity> findUserByPin(String pin);
}
