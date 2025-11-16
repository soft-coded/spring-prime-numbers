package com.example.primes.service;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Service("sieve")
public class SievePrimeService implements PrimeService {

    @Override
    @Cacheable("primes")
    public List<Long> primesUpTo(long max) {
        if (max < 2) return List.of();
        if (max > Integer.MAX_VALUE - 10) {
            throw new IllegalArgumentException("max too large for sieve implementation");
        }
        int n = (int) max;
        BitSet isComposite = new BitSet(n + 1);
        int limit = (int) Math.sqrt(n);
        for (int p = 2; p <= limit; p++) {
            if (!isComposite.get(p)) {
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    isComposite.set(multiple);
                }
            }
        }
        List<Long> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!isComposite.get(i)) primes.add((long) i);
        }
        return primes;
    }

    @Override
    public String algorithmName() { return "sieve"; }
}
