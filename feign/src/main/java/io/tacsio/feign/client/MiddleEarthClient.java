package io.tacsio.feign.client;

import io.tacsio.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "lotrClient", configuration = FeignConfig.class)
public interface MiddleEarthClient {

    @GetMapping
    String getCharacter();

    @PostMapping
    String getLocation();

    @PutMapping
    String getError();
}
