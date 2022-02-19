package com.fyeeme.ribbon;

import com.fyeeme.ribbon.config.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DiscoveryController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer")
    public String get() {
        return discoveryClient.consumer();
    }
}
