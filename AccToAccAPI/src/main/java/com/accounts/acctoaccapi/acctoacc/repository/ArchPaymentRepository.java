package com.accounts.acctoaccapi.acctoacc.repository;

import com.accounts.acctoaccapi.acctoacc.entity.ArchPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchPaymentRepository extends JpaRepository<ArchPaymentEntity, Long> {

}
