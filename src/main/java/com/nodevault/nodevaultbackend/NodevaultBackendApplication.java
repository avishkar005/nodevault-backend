package com.nodevault.nodevaultbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nodevault.nodevaultbackend.entity")
public class NodevaultBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodevaultBackendApplication.class, args);
    }
}