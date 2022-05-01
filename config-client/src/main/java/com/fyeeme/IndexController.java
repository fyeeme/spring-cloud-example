package com.fyeeme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${info.profile}")
    private String info;

    @GetMapping("/info")
    public String getInfo(){
        return info;
    }
}
