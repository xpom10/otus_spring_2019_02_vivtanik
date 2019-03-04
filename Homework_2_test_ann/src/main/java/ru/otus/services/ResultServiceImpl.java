package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.model.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {

    private Map<Integer, String> resultMessages = new HashMap<>();

    public ResultServiceImpl() {
        resultMessages.put(0, "Ни одного верного ответа, надо еще немного потренироваться");
        resultMessages.put(1, "Один правильный ответ, надо еще немного потренироваться");
        resultMessages.put(2, "Эх... двоечка, надо еще немного потренироваться");
        resultMessages.put(3, "Неплохо, ты явно интересуешься футболом");
        resultMessages.put(4, "Ты молодец, всего один промах");
        resultMessages.put(5, "5 из 5! Ты молодец");
    }

    @Override
    public void showResults(User user) {
        String message = resultMessages.getOrDefault(user.getCountRightAnswers(), "Не найдено столько ответов");
        System.out.println(message);
    }
}