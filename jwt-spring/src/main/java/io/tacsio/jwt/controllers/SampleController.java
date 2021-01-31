package io.tacsio.jwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/up")
    public String up() {
        return "It's up!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello world secured.";
    }
}
