package com.fyeeme.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client", fallbackFactory = DcClientFallback.class)
public interface DcClient {
    @GetMapping("/fallback/dc")
    String consumer();
}
