package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.messageSystem.MessageSystem;
import ru.otus.model.User;

@Service
public class ResultServiceImpl implements ResultService {

    private MessageSystem messageSystem;

    @Autowired
    public ResultServiceImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    @Override
    public void showResults(User user) {
        String message = messageSystem.getMessage(
                "result.message",
                new Object[]{user.getName(), user.getFamily(), user.getCountRightAnswers()}
        );
        System.out.println(message);
    }
}