package io.tacsio.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class CombiningReactiveTypes {

    @Test
    public void mergeFluxes() {
        //INFO: not guarantees the match order of fluxes
        Flux<String> characters = Flux.just("Ned", "Tyrion", "Daenerys")
                .delayElements(Duration.ofMillis(500));

        Flux<String> houses = Flux.just("Stark", "Lanister", "Targaryen")
                .delaySubscription(Duration.ofMillis(200))
                .delayElements(Duration.ofMillis(500));

        Flux<String> merged = characters.mergeWith(houses);

        StepVerifier.create(merged)
                .expectNext("Ned")
                .expectNext("Stark")
                .expectNext("Tyrion")
                .expectNext("Lanister")
                .expectNext("Daenerys")
                .expectNext("Targaryen")
                .verifyComplete();
    }


    @Test
    public void zipFluxes() {
        Flux<String> characters = Flux.just("Ned", "Tyrion", "Daenerys");
        Flux<String> houses = Flux.just("Stark", "Lanister", "Targaryen");

        Flux<Tuple2<String, String>> zipped = Flux.zip(characters, houses);
        StepVerifier.create(zipped)
                .expectNextMatches(t -> eq(t, "Ned", "Stark"))
                .expectNextMatches(t -> eq(t, "Tyrion", "Lanister"))
                .expectNextMatches(t -> eq(t, "Daenerys", "Targaryen"))
                .verifyComplete();
    }

    private static boolean eq(Tuple2<String, String> t, String name, String houses) {
        return t.getT1().equals(name) && t.getT2().equals(houses);
    }

    @Test
    public void zipFunction() {
        Flux<String> characters = Flux.just("Ned", "Tyrion", "Daenerys");
        Flux<String> houses = Flux.just("Stark", "Lanister", "Targaryen");

        Flux<String> zipped = Flux.zip(characters, houses,
                (character, house) -> String.format("%s, house %s", character, house));

        StepVerifier.create(zipped)
                .expectNext("Ned, house Stark")
                .expectNext("Tyrion, house Lanister")
                .expectNext("Daenerys, house Targaryen")
                .verifyComplete();
    }

    @Test
    public void firstEmitter() {
        //ignores slow flux
        Flux<String> shohoku = Flux.just("Hanamichi Sakuragi", "Kaede Rukawa", "Takenori Akagi");
        Flux<String> ryonan = Flux.just("Kicchou Fukuda", "Akira Sendoh", "Jun Uozumi")
                .delaySubscription(Duration.ofMillis(100));

        Flux<String> firstFlux = Flux.first(ryonan, shohoku);

        StepVerifier.create(firstFlux)
                .expectNext("Hanamichi Sakuragi")
                .expectNext("Kaede Rukawa")
                .expectNext("Takenori Akagi")
                .verifyComplete();
    }
}
