package ru.otus.runner;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.csvModel.CsvQuestionEntity;
import ru.otus.messageSystem.MessageSystem;
import ru.otus.model.User;
import ru.otus.services.AnswerService;
import ru.otus.services.RegisterService;
import ru.otus.services.ResultService;
import ru.otus.util.ScannerUtil;

import java.util.List;

public class TestRunnerServiceImpl implements TestRunnerService {

    private RegisterService registerService;
    private AnswerService answerService;
    private ResultService resultService;
    private MessageSystem messageSystem;

    private User user;

    @Autowired
    public TestRunnerServiceImpl(RegisterService register, AnswerService answer, ResultService result, MessageSystem messageSystem) {
        this.registerService = register;
        this.answerService = answer;
        this.resultService = result;
        this.messageSystem = messageSystem;
    }

    @Override
    public void test() {
        registerUser();
        enterAnswers();
        showResults();
    }

    private void showResults() {
        resultService.showResults(user);
    }

    private void enterAnswers() {
        List<CsvQuestionEntity> lines = answerService.getCsvLines();

        lines.forEach(line -> {
            System.out.println(line.getQuestion() + "?\n" + line.getAnswers());
            int expectedAnswer = ScannerUtil.enterInt();
            if (answerService.checkAnswer(expectedAnswer, line.getRightAnswer())) {
                user.increaseCountRightAnswers();
            }
        });
    }

    private void registerUser() {
        System.out.println(messageSystem.getMessage("enter.name", null));
        String name = ScannerUtil.enterString();
        System.out.println(messageSystem.getMessage("enter.family", null));
        String family = ScannerUtil.enterString();

        user = registerService.registerUser(name, family);
    }
}
