package io.tacsio.feign.config;

import com.github.javafaker.Faker;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final Faker faker;

    public FeignConfig(Faker faker) {
        this.faker = faker;
    }

    @Bean
    public RequestInterceptor authenticationInterceptor() {
        return template -> template.header("X-AUTH", getToken());
    }

    private String getToken() {
        return faker.crypto().sha256();
    }
}
