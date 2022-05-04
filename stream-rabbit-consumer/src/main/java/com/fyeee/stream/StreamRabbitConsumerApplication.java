package com.fyeee.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StreamRabbitConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamRabbitConsumerApplication.class, args);
	}

}
