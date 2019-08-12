package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class SpringHystrixMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringHystrixMain.class, args);
    }

}
