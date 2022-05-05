package com.fyeeme.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController  {

    final  DcClient dcClient;

    public DcController(DcClient dcClient) {
        this.dcClient = dcClient;
    }

    @GetMapping("/consumer")
    public String consumer() {
        return dcClient.consumer();
    }
}
