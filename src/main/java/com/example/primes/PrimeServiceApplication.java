package com.example.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrimeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrimeServiceApplication.class, args);
    }
}
