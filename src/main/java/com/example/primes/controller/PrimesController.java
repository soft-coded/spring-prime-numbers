package com.example.primes.controller;

import com.example.primes.dto.PrimeResponse;
import com.example.primes.service.PrimeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primes")
public class PrimesController {

    private final PrimeService sieveService;
    private final PrimeService trialService;

    public PrimesController(@Qualifier("sieve") PrimeService sieveService,
                            @Qualifier("trial") PrimeService trialService) {
        this.sieveService = sieveService;
        this.trialService = trialService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PrimeResponse> getPrimes(
            @RequestParam(name = "max") long max,
            @RequestParam(name = "algorithm", required = false, defaultValue = "sieve") String algorithm
    ) {
        if (max < 0) return ResponseEntity.badRequest().build();
        PrimeService service = chooseService(algorithm);
        List<Long> primes = service.primesUpTo(max);
        PrimeResponse resp = new PrimeResponse(max, primes, service.algorithmName());
        return ResponseEntity.ok(resp);
    }

    private PrimeService chooseService(String algorithm) {
        if (algorithm == null) return sieveService;
        switch (algorithm.toLowerCase()) {
            case "trial": return trialService;
            case "sieve":
            default: return sieveService;
        }
    }
}
