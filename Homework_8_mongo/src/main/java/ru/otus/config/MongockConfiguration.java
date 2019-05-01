package ru.otus.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@org.springframework.context.annotation.Configuration
public class MongockConfiguration {

    private static String PACKAGE = "ru.otus.changelog";

    @Bean
    public Mongock mongock(MongoProps props, MongoClient client, Environment environment) {
        return new SpringMongockBuilder(client, props.getDatabase(), PACKAGE)
                .setSpringEnvironment(environment)
                .setLockQuickConfig()
                .build();
    }
}
