package com.example.Idempotency.Idempotent.API.repository;

import com.example.Idempotency.Idempotent.API.entity.PaymentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaymentRepository extends JpaRepository<PaymentClass, Long> {
    Optional<PaymentClass> findBytransactionId(long transactionId);
}
