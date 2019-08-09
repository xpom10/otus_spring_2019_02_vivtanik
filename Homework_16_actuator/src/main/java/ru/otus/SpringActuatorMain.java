package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class SpringActuatorMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringActuatorMain.class, args);
    }

}
