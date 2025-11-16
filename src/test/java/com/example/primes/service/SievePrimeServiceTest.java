package com.example.primes.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SievePrimeServiceTest {

    private final SievePrimeService svc = new SievePrimeService();

    @Test
    void primesUpTo_10() {
        List<Long> primes = svc.primesUpTo(10);
        assertEquals(List.of(2L,3L,5L,7L), primes);
    }

    @Test
    void primesUpTo_0() {
        assertTrue(svc.primesUpTo(0).isEmpty());
    }
}
