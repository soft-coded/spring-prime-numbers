# Prime Service

A Spring Boot RESTful API that calculates all prime numbers up to and including a number provided by the user. The service supports multiple algorithms, JSON/XML content negotiation, caching, and includes unit + integration tests.

---

# Features

##### Spring Boot REST API
##### Two prime number algorithms
* **Sieve of Eratosthenes** 
* **Trial Division**
##### JSON & XML support via content negotiation
##### Response caching using Caffeine
##### Integration and unit tests

---

# How It Works

## 1. PrimeServiceApplication

This is the Spring Boot entry point. It also enables caching via `@EnableCaching`.

```java
@SpringBootApplication
@EnableCaching
public class PrimeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrimeServiceApplication.class, args);
    }
}
```

---

# Service Layer

The service layer defines how prime numbers are calculated.

## PrimeService Interface

Defines what each prime-calculation algorithm must provide.

```java
public interface PrimeService {
    List<Long> primesUpTo(long max);
    String algorithmName();
}
```

---

# Algorithm Implementations

## 1. SievePrimeService (Default)

Efficient for generating all primes up to a number.

* Uses a `BitSet` to mark composite numbers.
* Time complexity: ~O(n log log n)
* Suitable for larger inputs

Caching (`@Cacheable`) ensures repeated calls with the same `max` value are fast.

## 2. TrialDivisionPrimeService

Simpler, slower algorithm.

* Tests divisibility up to `sqrt(n)`
* Good for small inputs or demonstration

Also cached.

---

# Controller

The REST endpoint lives in `PrimesController`.

### Endpoint: `GET /primes`

Parameters:

* `max` *(required)* — highest number to consider
* `algorithm` *(optional)* — `sieve` (default) or `trial`

### Content negotiation:

* `Accept: application/json` → JSON
* `Accept: application/xml` → XML

### Example JSON response:

```json
{
  "requested": 10,
  "primes": [2, 3, 5, 7],
  "algorithm": "sieve"
}
```

---

# Caching

Caching is handled by Spring Boot + Caffeine.

Benefits:

* Speed up repeated requests
* Reduce CPU computation

Configuration in `application.yml`:

```yaml
caffeine:
  spec: maximumSize=500,expireAfterAccess=10m
```

---

# Testing

## Unit Tests (JUnit 5)

Each algorithm class has tests verifying correctness.

* `SievePrimeServiceTest`
* `TrialDivisionPrimeServiceTest`

## Integration Tests (Rest Assured)

* Launches Spring Boot on a random port
* Verifies JSON & XML responses
* Ensures controller + service + serialization all work together

---

# Building & Running

### Build

```bash
mvn clean package
```

### Run

```bash
java -jar target/prime-service-0.1.0.jar
```

Or:

```bash
mvn spring-boot:run
```

### Test

```bash
mvn test
```

---

# API Examples

### JSON example

```bash
curl -H "Accept: application/json" "http://localhost:8080/primes?max=30"
```

### XML example

```bash
curl -H "Accept: application/xml" "http://localhost:8080/primes?max=30"
```

### Use trial division

```bash
curl "http://localhost:8080/primes?max=100&algorithm=trial"
```