package com.example.Idempotency.Idempotent.API.controller;


import com.example.Idempotency.Idempotent.API.entity.PaymentClass;
import com.example.Idempotency.Idempotent.API.service.PaymentServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImplement payService;

    @PostMapping("/process")
    public String processPayment(@RequestHeader("idempotecy-key") String IdempotencyKey,@RequestBody PaymentClass paymentClass){
        return payService.processPayment(paymentClass, IdempotencyKey);
    }
}
