package com.example.primes.service;

import java.util.List;

public interface PrimeService {
    List<Long> primesUpTo(long max);
    String algorithmName();
}
