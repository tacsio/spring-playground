package io.tacsio.reactive;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

class CreatingReactiveTypes {

    @Test
    void createFromJust() {
        Flux<String> fruits = Flux.just("Apple", "Orange", "Grape", "Banana");
        fruits.subscribe(s -> System.out.println("Fruit " + s));

        StepVerifier.create(fruits)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void createFromArray() {
        String[] fruitsArray = new String[]{"Apple", "Orange", "Grape", "Banana"};
        Flux<String> fruits = Flux.fromArray(fruitsArray);

        StepVerifier.create(fruits)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void createFromList() {
        List<String> fruitList = Lists.list("Apple", "Orange", "Grape", "Banana");
        Flux<String> fruits = Flux.fromIterable(fruitList);

        StepVerifier.create(fruits)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void createFromStream() {
        Stream<String> fruitStream = Stream.of("Apple", "Orange", "Grape", "Banana");
        Flux<String> fruits = Flux.fromStream(fruitStream);

        StepVerifier.create(fruits)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void createFromRange() {
        Flux<Integer> range = Flux.range(1, 5);

        StepVerifier.create(range)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    void createFromInterval() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1)).take(5);

        StepVerifier.create(interval)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                .verifyComplete();
    }

}