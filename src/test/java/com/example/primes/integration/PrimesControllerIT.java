package com.example.primes.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PrimesControllerIT {

    @LocalServerPort
    int port;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void getPrimes_json_default() {
        given()
            .port(port)
            .accept(ContentType.JSON)
            .queryParam("max", 10)
        .when()
            .get("/primes")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("requested", equalTo(10))
            .body("primes.size()", equalTo(4))
            .body("primes", hasItems(2,3,5,7));
    }

    @Test
    void getPrimes_xml() {
        given()
            .port(port)
            .accept(ContentType.XML)
            .queryParam("max", 5)
        .when()
            .get("/primes")
        .then()
            .statusCode(200)
            .contentType(ContentType.XML)
            .body(hasXPath("/PrimeResponse/requested[text()='5']"));
    }
}
