package io.tacsio.feign.config;

import com.github.javafaker.Faker;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class FeignConfig {

    private final Logger log = LoggerFactory.getLogger(FeignConfig.class);

    private final Faker faker;

    public FeignConfig(Faker faker) {
        this.faker = faker;
    }

    @Bean
    public RequestInterceptor authenticationInterceptor() {
        return template -> template.header("X-AUTH", getToken());
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            log.info("key: {} body: {}", methodKey, response);
            log.error("Error {} in {}. Reason: {}", response.status(), methodKey, response.reason());

            throw new ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason());
        };
    }

    private String getToken() {
        return faker.crypto().sha256();
    }
}
