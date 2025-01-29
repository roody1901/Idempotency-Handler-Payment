package com.example.Idempotency.Idempotent.API.service;


import com.example.Idempotency.Idempotent.API.entity.PaymentClass;
import com.example.Idempotency.Idempotent.API.entity.PaymentStatus;
import com.example.Idempotency.Idempotent.API.repository.PaymentRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImplement implements PaymentService {

    private PaymentRepository payRepo;
    private StringRedisTemplate redisTemplate;

    public PaymentServiceImplement(PaymentRepository payRepo, StringRedisTemplate redisTemplate){
        this.payRepo = payRepo;
        this.redisTemplate = redisTemplate;
    }

    //can implement re-try here
    @Override
    public String processPayment(PaymentClass payClass, String randomUID) {
        try{
            String redisKey = "idempotecy-key:"+ randomUID;
            String exisitingKey = redisTemplate.opsForValue().get(redisKey);

            if(exisitingKey != null){
                return "Duplicate request : Transaction already processed";
            }

//            Optional<PaymentClass> existingPayment = payRepo.findBytransactionId(payClass.getTransactionId());
//            if(existingPayment.isPresent()){
//                return "Duplicate request : Transaction already processed";
//            }

            String response = paymentProcessLogic(payClass);
            payClass.setPaymentStatus(String.valueOf(PaymentStatus.success));
            payRepo.save(payClass);

            redisTemplate.opsForValue().set(redisKey,response,5, TimeUnit.MINUTES);
            return "Payment completed";
        }
        catch(Exception ex){
            throw new RuntimeException("Got this exception: "+ex);
        }
    }

    private String paymentProcessLogic(PaymentClass paymentClass){

        //here we can do alot of business logics like , credit , debit etc to show payment
        // now because of idempotent call same user whoes any request get failed did not have to pay multiple time
         return "Payment done for transaction:"+paymentClass.getTransactionId();
    }
}
