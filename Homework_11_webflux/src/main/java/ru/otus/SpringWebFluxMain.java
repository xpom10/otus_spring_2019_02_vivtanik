package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringWebFluxMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxMain.class, args);
    }

}
