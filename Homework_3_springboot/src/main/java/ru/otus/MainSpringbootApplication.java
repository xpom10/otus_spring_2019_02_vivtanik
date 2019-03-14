package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.runner.TestRunnerService;
import ru.otus.runner.TestRunnerServiceImpl;

@SpringBootApplication
public class MainSpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainSpringbootApplication.class, args);
        TestRunnerService testRunnerService = context.getBean(TestRunnerServiceImpl.class);
        testRunnerService.test();
    }

}
