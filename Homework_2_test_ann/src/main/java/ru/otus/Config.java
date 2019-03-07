package ru.otus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.messageSystem.MessageImpl;
import ru.otus.messageSystem.MessageSystem;
import ru.otus.runner.TestRunnerService;
import ru.otus.runner.TestRunnerServiceImpl;
import ru.otus.services.AnswerService;
import ru.otus.services.AnswerServiceImpl;
import ru.otus.services.RegisterService;
import ru.otus.services.ResultService;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Config {

    @Bean
    public AnswerService answerService(@Value("${csv.filename}") String filename, MessageSystem messageSystem) {
        return new AnswerServiceImpl(filename, messageSystem);
    }

    @Bean
    public MessageSystem messageSystem(MessageSource messageSource, @Value("${locale}") String locale) {
        return new MessageImpl(messageSource, locale);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public TestRunnerService testRunnerService(RegisterService register, AnswerService answer, ResultService result, MessageSystem messageSystem) {
        return new TestRunnerServiceImpl(register, answer, result, messageSystem);
    }

}
