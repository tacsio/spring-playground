package io.tacsio.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/got")
public class GOTController {

    private final Logger log = LoggerFactory.getLogger(GOTController.class);
    private final Faker faker = new Faker();

    @GetMapping
    public ResponseEntity<?> get() {
        var msg = "%s at %s".formatted(faker.gameOfThrones().character(), faker.gameOfThrones().city());
        log.info("GET {} ", msg);

        return ResponseEntity.ok(msg);
    }

    @PostMapping
    public ResponseEntity<?> post() {
        var person = faker.gameOfThrones().character();
        log.info("POST {}", person);

        return ResponseEntity.ok(person);
    }
}
