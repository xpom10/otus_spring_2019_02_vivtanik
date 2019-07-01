package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class SpringSecurityMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityMain.class, args);
    }

}
