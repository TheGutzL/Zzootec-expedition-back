package com.zzootec.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesProductsApplication.class, args);
    }

}
