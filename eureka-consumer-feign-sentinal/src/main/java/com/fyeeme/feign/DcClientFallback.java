package com.fyeeme.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DcClientFallback implements FallbackFactory<DcClient> {
    @Override
    public DcClient create(Throwable cause) {
        return new DcClient() {
            @Override
            public String consumer() {
                return "服务暂时不可用";
            }
        };
    }
}
