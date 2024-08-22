package io.tacsio.feign.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
