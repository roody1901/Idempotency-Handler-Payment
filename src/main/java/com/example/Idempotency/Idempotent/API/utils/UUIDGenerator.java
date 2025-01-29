package com.example.Idempotency.Idempotent.API.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
public class UUIDGenerator {


    public String UUID(){
        UUID uuid = UUID.randomUUID();
        String uUID = uuid.toString();
        return uUID;
    }
}
