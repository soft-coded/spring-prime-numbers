package com.example.primes.dto;

import java.util.List;

public class PrimeResponse {
    private long requested;
    private List<Long> primes;
    private String algorithm;

    public PrimeResponse() {}

    public PrimeResponse(long requested, List<Long> primes, String algorithm) {
        this.requested = requested;
        this.primes = primes;
        this.algorithm = algorithm;
    }

    public long getRequested() { return requested; }
    public void setRequested(long requested) { this.requested = requested; }

    public List<Long> getPrimes() { return primes; }
    public void setPrimes(List<Long> primes) { this.primes = primes; }

    public String getAlgorithm() { return algorithm; }
    public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }
}
