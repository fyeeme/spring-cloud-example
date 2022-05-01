package com.fyeeme.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class discoveryController {

    @GetMapping("/index")
    public String get(@RequestParam String name) {
        log.info("Invoked name:  {}", name);
        return name;
    }
}
