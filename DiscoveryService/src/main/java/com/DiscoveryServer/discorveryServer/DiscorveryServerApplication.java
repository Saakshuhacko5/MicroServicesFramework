package com.DiscoveryServer.discorveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// we add eureka server bec. => where all client applications can register by themselves
// and other microservices look up the Eureka Server to get independent microservices to get the job complete.
@EnableEurekaServer
public class DiscorveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscorveryServerApplication.class, args);
    }
}
