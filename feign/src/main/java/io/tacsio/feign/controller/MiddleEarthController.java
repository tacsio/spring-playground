package io.tacsio.feign.controller;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotr")
public class MiddleEarthController {

    private static final Logger log = LoggerFactory.getLogger(MiddleEarthController.class);

    private final Faker faker;

    public MiddleEarthController(Faker faker) {
        this.faker = faker;
    }

    @GetMapping
    public String character(@RequestHeader("X-AUTH") String token) {
        log.info("TOKEN: {}", token);

        return faker.lordOfTheRings().character();
    }

    @PostMapping
    public String location(@RequestHeader("X-AUTH") String token) {
        log.info("TOKEN: {}", token);

        return faker.lordOfTheRings().location();
    }
}
