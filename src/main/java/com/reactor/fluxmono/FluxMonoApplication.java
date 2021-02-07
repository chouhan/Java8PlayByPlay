package com.reactor.fluxmono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/*
 * Reactor Core is a Java 8 library which implements the reactive programming model.
 * It's built on top of the Reactive Streams Specification, a standard for building reactive applications.
 * Reactor Core is different from Java 8 Stream API.
 * Flux and Mono are implementations of the Reactive Streams Publisher interface.
 *
 *
 * */
//@SpringBootApplication
public class FluxMonoApplication {

    public static void main(String[] args) {
        createFluxStream();
        //createFluxStreamWithListeners();
        createFluxStreamWithMap();
        createFluxStreamWithFlatMap();
        createFluxStreamFromList();
        createFluxStreamFromStream();
        createFluxStreamFromRange();
        createFluxStreamFromInterval();
        createFluxStreamFromAnotherFlux();
        // SpringApplication.run(FluxMonoApplication.class, args);
    }

    // prints 1,2,3
    private static void createFluxStream() {
        System.out.println("Creating Flux Stream");
        Flux<String> just = Flux.just("1", "2", "3");
        just.subscribe(System.out::println);
    }

    // prints  12,22,32
    private static void createFluxStreamWithMap() {
        System.out.println("Creating Flux Stream with Map");
        Flux<String> just = Flux.just("1", "2", "3");
        just.map(nr -> nr + " with map").subscribe(System.out::println);
    }

    // prints  12,22,32
    private static void createFluxStreamWithFlatMap() {
        System.out.println("Creating Flux Stream with FlatMap");
        Flux<String> just = Flux.just("1", "2", "3");
        just.flatMap(nr -> Mono.just(nr + " with flatMap")).subscribe(System.out::println);
    }

    private static void createFluxStreamFromList() {
        System.out.println("Creating Flux Stream from List");
        List<String> stringList = Arrays.asList("This", "is", "a", "list", "of", "strings", "from", "flux");
        Flux<String> stringFlux = Flux.fromIterable(stringList);
        stringFlux.map(str -> str).subscribe(System.out::println);
    }

    private static void createFluxStreamFromStream() {
        System.out.println("Creating Flux stream from stream");
        Stream<String> stringStream = Stream.of("This", "is", "a", "list", "of", "strings", "from", "flux");
        Flux<String> fluxFromStream = Flux.fromStream(stringStream);
        fluxFromStream.map(str -> str).subscribe(System.out::println);
    }

    private static void createFluxStreamFromRange() {
        System.out.println("Creating Flux Stream from Range");
        Flux<Integer> rangeFlux = Flux.range(1, 5); // Flux(1,2,3,4,5)
        rangeFlux.map(range -> range).subscribe(System.out::println);
    }

    private static void createFluxStreamFromInterval() {
        System.out.println("Creating Flux Stream from Interval");
        Flux<Long> intervalFlux = Flux.interval(Duration.ofMillis(10));
        intervalFlux.map(interval -> interval).subscribe(System.out::println);
    }

    private static void createFluxStreamFromAnotherFlux() {
        System.out.println("Creating Flux Stream from Another Flux");
        Flux<Long> intervalFlux = Flux.interval(Duration.ofMillis(10));
        Flux<Long> copyFlux = Flux.from(intervalFlux);
        copyFlux.map(interval -> interval).subscribe(System.out::println);
    }

}