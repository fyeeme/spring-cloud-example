package com.fyeee.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// @Configuration
@Slf4j
public class StreamConfig {
    @Bean
    public Supplier<Task> publish() {
        return () -> {
            Task task = new Task.TaskBuilder()
                    .id(UUID.randomUUID().toString())
                    .title("sms send to user")
                    .description("a good news for you")
                    .taskStatus(Status.SUBSCRIBED)
                    .build();
            log.info("Publishing task: " + task);
            return task;
        };
    }

    @Bean
    public Function<Task, Task> process() {
        return task -> {
            task.setTaskStatus(Status.PROCESSED);
            log.info("Processing task: " + task);
            return task;
        };
    }

    @Bean
    public Consumer<Task> subscribe() {
        return task -> {
            task.setTaskStatus(Status.SUBSCRIBED);
            log.info("Subscribed task: " + task);
        };
    }
}
