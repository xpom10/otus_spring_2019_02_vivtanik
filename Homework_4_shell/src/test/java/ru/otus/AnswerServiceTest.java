package ru.otus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.messageService.MessageServiceImpl;
import ru.otus.services.AnswerService;
import ru.otus.services.AnswerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.location:application.yml", classes = {AnswerServiceImpl.class, MessageServiceImpl.class})
public class AnswerServiceTest {

    @Autowired
    AnswerService answerService;

    @Test
    public void testFalse() {
        boolean isActual = answerService.checkAnswer(1, 2);
        assertFalse(isActual);
    }

    @Test
    public void testTrue() {
        boolean isActual = answerService.checkAnswer(1, 1);
        assertTrue(isActual);
    }
}
