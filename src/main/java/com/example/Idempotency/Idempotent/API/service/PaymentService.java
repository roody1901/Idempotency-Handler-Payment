package com.example.Idempotency.Idempotent.API.service;

import com.example.Idempotency.Idempotent.API.entity.PaymentClass;

public interface PaymentService {

    String processPayment(PaymentClass payClass, String randomUID);
}
