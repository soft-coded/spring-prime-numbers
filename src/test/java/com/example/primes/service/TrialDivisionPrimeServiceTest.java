package com.example.primes.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class TrialDivisionPrimeServiceTest {

    private final TrialDivisionPrimeService svc = new TrialDivisionPrimeService();

    @Test
    void primesUpTo_10() {
        List<Long> primes = svc.primesUpTo(10);
        assertEquals(List.of(2L,3L,5L,7L), primes);
    }
}
