package com.example.primes.service;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

@Service("trial")
public class TrialDivisionPrimeService implements PrimeService {

    @Override
    @Cacheable("primes")
    public List<Long> primesUpTo(long max) {
        List<Long> primes = new ArrayList<>();
        if (max < 2) return primes;
        for (long n = 2; n <= max; n++) {
            if (isPrime(n)) primes.add(n);
        }
        return primes;
    }

    private boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        long r = (long) Math.sqrt(n);
        for (long i = 3; i <= r; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public String algorithmName() { return "trial"; }
}
