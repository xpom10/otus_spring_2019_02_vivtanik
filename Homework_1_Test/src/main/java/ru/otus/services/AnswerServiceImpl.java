package ru.otus.services;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.csvModel.CsvQuestionEntity;
import ru.otus.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {


    @Override
    public boolean checkAnswer(int expectedAnswer, int actualAnswer) {
        return expectedAnswer == actualAnswer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CsvQuestionEntity> getCsvLines() {
        Path path = Paths.get(ClassLoader.getSystemResource("csv/Questions.csv").getPath());

        return new CsvToBeanBuilder(readCsv(path)).withSeparator(';')
                .withType(CsvQuestionEntity.class).build().parse();
    }

    @Override
    public void showResults(User user) {
        switch (user.getCountRightAnswers()) {
            case 0:
            System.out.println(String.format("Ни одного верного ответа, %s, надо еще немного потренироваться", user.getName()));
            break;
            case 1:
                System.out.println(String.format("Один правильный ответ, %s, надо еще немного потренироваться", user.getName()));
                break;
            case 2:
                System.out.println(String.format("Эх... двоечка, %s, надо еще немного потренироваться", user.getName()));
                break;
            case 3:
                System.out.println(String.format("Неплохо, %s, ты явно интересуешься футболом", user.getName()));
                break;
            case 4:
                System.out.println(String.format("Ты молодец, %s, всего один промах", user.getName()));
                break;
            case 5:
                System.out.println(String.format("5 из 5! %s, ты молодец", user.getName()));
                break;
            default:
                System.out.println(String.format("Такого результата я не ожидал: %s", user.getCountRightAnswers()));
                break;
        }
    }

    private FileReader readCsv(Path path) {
        try {
            return new FileReader(path.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не удалось прочитать файл.", e);
        }
    }
}
