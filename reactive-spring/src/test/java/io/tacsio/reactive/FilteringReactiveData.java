package io.tacsio.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FilteringReactiveData {

    @Test
    public void skipFew() {
        Flux<Integer> skips = Flux.just(1, 2, 3, 4, 5)
                .skip(3);

        StepVerifier.create(skips)
                .expectNext(4, 5)
                .verifyComplete();
    }

    @Test
    public void skipFewSeconds() {
        Flux<Integer> skips = Flux.just(1, 2, 3, 4, 5)
                .delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(4));

        StepVerifier.create(skips)
                .expectNext(4, 5)
                .verifyComplete();
    }

    @Test
    public void take() {
        Flux<Integer> takes = Flux.range(1, 5)
                .take(3);

        StepVerifier.create(takes)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    @Test
    public void takeForFewSeconds() {
        Flux<Integer> takes = Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .take(Duration.ofMillis(3500));

        StepVerifier.create(takes)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    @Test
    public void filter() {
        Flux<Integer> filter = Flux.range(1, 5)
                .filter(i -> i % 2 == 0);

        StepVerifier.create(filter)
                .expectNext(2, 4)
                .verifyComplete();
    }

    @Test
    public void filterDistinct() {
        Flux<String> languages = Flux.just("Java", "Javascript", "Ruby", "Python", "Ruby", "C#", "Java", "C#")
                .distinct();

        StepVerifier.create(languages)
                .expectNext("Java", "Javascript", "Ruby","Python", "C#")
                .verifyComplete();
    }
}
