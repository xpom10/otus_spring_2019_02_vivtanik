package ru.otus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.runner.TestRunnerService;
import ru.otus.runner.TestRunnerServiceImpl;
import ru.otus.services.*;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Config {

    @Bean
    public AnswerService answerService(@Value("${csv.filename}") String filename) {
        return new AnswerServiceImpl(filename);
    }

    @Bean
    public TestRunnerService testRunnerService(RegisterService register, AnswerService answer, ResultService result) {
        return new TestRunnerServiceImpl(register, answer, result);
    }
}
