package io.tacsio.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TransformingReactiveData {

    //Mapping is performed synchronously
    //To work asynchronously we should use flatMap
    @Test
    public void map() {
        Flux<Player> shohoku = Flux.just("Hanamichi Sakuragi", "Kaede Rukawa", "Takenori Akagi")
                .map(name -> {
                    String[] split = name.split(" ");
                    return new Player(split[0], split[1]);
                });

        StepVerifier.create(shohoku)
                .expectNext(new Player("Hanamichi", "Sakuragi"))
                .expectNext(new Player("Kaede", "Rukawa"))
                .expectNext(new Player("Takenori", "Akagi"))
                .verifyComplete();
    }

    @Test
    public void flatMap() {
        Flux<Player> playerFlux = Flux.just("Hanamichi Sakuragi", "Kaede Rukawa", "Takenori Akagi")
                .flatMap(n -> Mono.just(n)
                        .map(p -> {
                            String[] split = p.split("\\s");
                            return new Player(split[0], split[1]);
                        }).subscribeOn(Schedulers.parallel()));

        List<Player> playerList = Arrays.asList(
                new Player("Hanamichi", "Sakuragi"),
                new Player("Kaede", "Rukawa"),
                new Player("Takenori", "Akagi"));

        StepVerifier.create(playerFlux)
                .expectNextMatches(playerList::contains)
                .expectNextMatches(playerList::contains)
                .expectNextMatches(playerList::contains)
                .verifyComplete();
    }


    @Test
    public void comparison() {
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        Flux<Item> itemFlux = Flux.fromIterable(list)
                .map(Item::new)
                .subscribeOn(Schedulers.parallel());

        //will print NEW and the return of toString synchronously
        //even if we use subscribeOn parallel, the map function still runs synchronously
        //to run async mode, we need to use transform into a flatMap and then use Mono
        //to ensure that every subscription will run in parallel.
        System.out.println("MAP");
        StepVerifier.create(itemFlux)
                .thenConsumeWhile(item -> {
                    System.out.println(item);
                    return true;
                }).verifyComplete();

        Flux<Item> itemFluxFlat = Flux.fromIterable(list)
                .flatMap(d -> Mono.just(d)
                        .map(Item::new)
                        .subscribeOn(Schedulers.parallel()));

        //since my machine has 4 cores, will print (randomly) 4 NEWs
        //and then toString of those Items
        //after that will print another NEW and toString of that Item
        System.out.println("FLAT MAP");
        StepVerifier.create(itemFluxFlat)
                .thenConsumeWhile(item -> {
                    System.out.println(item);
                    return true;
                }).verifyComplete();

        //with elastic scheduler, in this example, all Items will be handled
        //by their onw threads printing NEW 5 times (in list order)
        //and then printing toString in numeric order since the thread of
        //last item o list will awake sooner.
        Flux<Item> itemFluxFlatElastic = Flux.fromIterable(list)
                .flatMap(d -> Mono.just(d)
                        .map(Item::new)
                        .subscribeOn(Schedulers.elastic()));

        System.out.println("FLAT MAP ELASTIC");
        StepVerifier.create(itemFluxFlatElastic)
                .thenConsumeWhile(item -> {
                    System.out.println(item);
                    return true;
                }).verifyComplete();
    }
}

class Player {
    public final String firstName;
    public final String lastName;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(firstName, player.firstName) &&
                Objects.equals(lastName, player.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

class Item {
    public final Integer seconds;

    public Item(Integer seconds) {
        this.seconds = seconds;
        try {
            System.out.println("NEW " + seconds);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "seconds=" + seconds +
                '}';
    }
}