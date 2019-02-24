package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.csvModel.CsvQuestionEntity;
import ru.otus.model.User;
import ru.otus.services.AnswerService;
import ru.otus.services.RegisterService;
import ru.otus.util.ScannerUtil;

import java.util.List;

import static ru.otus.util.ScannerUtil.closeScanner;

public class Main {

    private static User user;
    private static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/spring.xml");


    public static void main(String[] args) {
        registerUser();
        enterAnswers();
        showResults();
        closeScanner();
    }

    private static void showResults() {
        AnswerService answerService = context.getBean(AnswerService.class);
        answerService.showResults(user);
    }

    private static void enterAnswers() {
        AnswerService answerService = context.getBean(AnswerService.class);
        List<CsvQuestionEntity> lines = answerService.getCsvLines();

        lines.forEach(line -> {
            System.out.println(line.getQuestion() + "?\n" + line.getAnswers());
            int expectedAnswer = ScannerUtil.enterInt();
            if (answerService.checkAnswer(expectedAnswer, line.getRightAnswer())) {
                user.increaseCountRightAnswers();
            }
        });
    }

    private static void registerUser() {
        RegisterService registerService = context.getBean(RegisterService.class);

        System.out.println("Введите Ваше имя:");
        String name = ScannerUtil.enterString();
        System.out.println("Введите вашу фамилию:");
        String family = ScannerUtil.enterString();

        user = registerService.registerUser(name, family);
    }
}
