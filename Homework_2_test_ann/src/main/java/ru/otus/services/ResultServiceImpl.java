package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.messageService.MessageService;
import ru.otus.model.User;

@Service
public class ResultServiceImpl implements ResultService {

    private MessageService messageService;

    @Autowired
    public ResultServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void showResults(User user) {
        String message = messageService.getMessage(
                "result.message",
                new Object[]{user.getName(), user.getFamily(), user.getCountRightAnswers()}
        );
        System.out.println(message);
    }
}