package io.tacsio.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class BufferingReactiveData {

    @Test
    void simpleBuffer() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        Flux<List<Integer>> buffered = flux.buffer(3);

        StepVerifier.create(buffered)
                .expectNext(Arrays.asList(1, 2, 3))
                .expectNext(Arrays.asList(4, 5))
                .verifyComplete();
    }

    @Test
    void reactiveBuffer() {
        Flux.just("one", "two", "three", "four", "five")
                .buffer(3)
                .flatMap(i -> Flux.fromIterable(i)
                        .map(String::toUpperCase)
                        .subscribeOn(Schedulers.parallel())
                        .log())
                .subscribe();
    }
}
