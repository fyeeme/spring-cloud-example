package com.fyeee.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
public class MessageController {
    private final ObjectMapper objectMapper;
    // EmitterProcessor is deprecated and recommend to use Sinks
    // private final EmitterProcessor<Message<?>> processor = EmitterProcessor.create();
    public final Sinks.Many<Message<?>> sink;

    public MessageController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @SuppressWarnings("unchecked")
    @PostMapping(path = "/", consumes = "*/*")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest(@RequestBody String body, @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType) throws Exception {
        Map<String, String> payload = objectMapper.readValue(body, Map.class);
        String destinationName = payload.get("id");
        Message<?> message = MessageBuilder.withPayload(payload)
                .setHeader("spring.cloud.stream.sendto.destination", destinationName).build();
        sink.tryEmitNext(message);
    }

    @Bean
    public Supplier<Flux<Message<?>>> supplier() {
        return sink::asFlux;
    }

    // Following sink is used as test consumer. It logs the data received through the consumer.
    @Slf4j
    static class TestSink {

        @Bean
        public Consumer<String> receive1() {
            return data -> log.info("Data received from customer-1..." + data);
        }

        @Bean
        public Consumer<String> receive2() {
            return data -> log.info("Data received from customer-2..." + data);
        }
    }

}
