package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class SpringDockerMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringDockerMain.class, args);
    }

}
