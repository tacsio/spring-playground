package io.tacsio.api.controller;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    private final Logger log = LoggerFactory.getLogger(SampleController.class);
    private final Faker faker = new Faker();

    @GetMapping
    public ResponseEntity<?> sample() {
        var msg = "%s at %s".formatted(faker.lordOfTheRings().character(), faker.lordOfTheRings().location());
//        var msg = "%s at %s".formatted(person, faker.lordOfTheRings().location());

        log.info(msg);
        return ResponseEntity.ok(msg);
    }

    @PostMapping
    public ResponseEntity<?> samplePost() {
        return ResponseEntity.ok(faker.pokemon());
    }
}
