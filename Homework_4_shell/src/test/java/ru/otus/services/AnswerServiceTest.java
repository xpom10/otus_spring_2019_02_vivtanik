package ru.otus.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.csvModel.CsvQuestionEntity;
import ru.otus.messageService.MessageService;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@TestPropertySource(properties = "application.csv.filename=TestQuestions.csv")
public class AnswerServiceTest {

    @MockBean
    private MessageService messageService;

    @Autowired
    private AnswerService answerService;

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

    @Test
    public void testGetEnAnswers() {
        Mockito.when(messageService.getLocale()).thenReturn(Locale.forLanguageTag("en"));
        List<CsvQuestionEntity> englishqQuestions = answerService.getCsvLines();

        assertEquals(3, englishqQuestions.size());
    }

    @Test
    public void testGetRuAnswers() {
        Mockito.when(messageService.getLocale()).thenReturn(Locale.forLanguageTag("ru"));
        List<CsvQuestionEntity> russianQuestions = answerService.getCsvLines();

        assertEquals(4, russianQuestions.size());
    }

}
