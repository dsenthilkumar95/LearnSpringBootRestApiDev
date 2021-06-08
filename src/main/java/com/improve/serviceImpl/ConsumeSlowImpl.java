package com.improve.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.improve.service.ConsumeSlow;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ConsumeSlowImpl implements ConsumeSlow {
    @Override
    public String consumeSlowProcess(){
        List<String> outputList = new ArrayList<>();
        WebClient webClient = WebClient.create("http://localhost:8080");
        Flux<JsonNode> mergedFlux = Flux.merge(webClient.get().uri("/api/slow/{secs}", 5).header("Content-Type", "application/json").retrieve().bodyToMono(JsonNode.class),
                webClient.get().uri("/api/slow/{secs}", 6).header("Content-Type", "application/json").retrieve().bodyToMono(JsonNode.class),
                webClient.get().uri("/api/slow/{secs}", 7).header("Content-Type", "application/json").retrieve().bodyToMono(JsonNode.class));
        mergedFlux.parallel().runOn(Schedulers.elastic()).sequential().collectList().block().forEach(node -> {
            outputList.add(node.get("output").toString());
        });
        return outputList.toString();
//        final List<Mono<String>> responseMonos = IntStream.range(6, 8).mapToObj(
//                index -> webClient.get().uri("/api/slow/{secs}", index).header("Content-Type", "application/json").retrieve().bodyToMono(String.class)).collect(Collectors.toList());
//        Mono.zip(responseMonos, Arrays::asList) // make parallel network calls and collect it to a list
//                .flatMapIterable(objects -> objects) // make flux of objects
//                .doOnComplete(() -> System.out.println("Done"))// will be printed on completion of the flux created above
//                .subscribe(responseString -> {System.out.println("responseString = " + responseString);
//                    outputList.add(responseString.toString());});
//        return outputList.toString();
    }
}
