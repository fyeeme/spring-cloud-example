package com.fyeeme.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String get() {
        String services = String.format("Services: %s", discoveryClient.getServices());
        log.info("{}", services);
        return services;
    }

    @GetMapping("/client/dc")
    public String fallbackDc() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5L);
        return "fallbackDc";
    }
}
