package io.tacsio.feign.client;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class MiddleEarthClientController {

    private final MiddleEarthClient client;

    public MiddleEarthClientController(MiddleEarthClient client) {
        this.client = client;
    }


    @PostMapping
    public String character() {
        return client.getCharacter();
    }

    @GetMapping
    public String location() {
        return client.getLocation();
    }

    @PutMapping
    public String error() {
        return client.getError();
    }
}
