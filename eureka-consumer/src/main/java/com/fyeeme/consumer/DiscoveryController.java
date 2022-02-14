package com.fyeeme.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class DiscoveryController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String get() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = String.format("http://%s:%s/dc", serviceInstance.getHost(), serviceInstance.getPort());
        log.info("{}", url);

        return restTemplate.getForObject(url, String.class);
    }
}
