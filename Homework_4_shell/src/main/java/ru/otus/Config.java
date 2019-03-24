package ru.otus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.messageService.MessageService;
import ru.otus.messageService.MessageServiceImpl;
import ru.otus.runner.TestRunnerService;
import ru.otus.runner.TestRunnerServiceImpl;
import ru.otus.services.AnswerService;
import ru.otus.services.AnswerServiceImpl;
import ru.otus.services.RegisterService;
import ru.otus.services.ResultService;

@Configuration
public class Config {

    @Bean
    public AnswerService answerService(@Value("${application.csv.filename}") String filename, MessageService messageService) {
        return new AnswerServiceImpl(filename, messageService);
    }

    @Bean
    public MessageService messageService(MessageSource messageSource, @Value("${application.locale}") String locale) {
        return new MessageServiceImpl(messageSource, locale);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public TestRunnerService testRunnerService(RegisterService register, AnswerService answer, ResultService result, MessageService messageService) {
        return new TestRunnerServiceImpl(register, answer, result, messageService);
    }

}
