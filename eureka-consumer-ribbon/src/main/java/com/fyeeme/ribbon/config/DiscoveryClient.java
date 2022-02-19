package com.fyeeme.ribbon.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface DiscoveryClient {

    @GetMapping("/dc")
    String consumer();
}
