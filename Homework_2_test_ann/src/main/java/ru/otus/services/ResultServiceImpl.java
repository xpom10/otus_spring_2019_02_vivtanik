package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.model.User;

import java.util.Locale;

@Service
public class ResultServiceImpl implements ResultService {

    private MessageSource messageSource;
    private Locale locale;

    @Autowired
    public ResultServiceImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public void showResults(User user) {
        String message = messageSource.getMessage(
                "result.message",
                new Object[]{user.getName(), user.getFamily(), user.getCountRightAnswers()},
                locale
        );
        System.out.println(message);
    }
}