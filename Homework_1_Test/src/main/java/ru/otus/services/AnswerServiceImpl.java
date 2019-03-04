package ru.otus.services;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.csvModel.CsvQuestionEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class AnswerServiceImpl implements AnswerService {

    private String filename;

    public AnswerServiceImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean checkAnswer(int expectedAnswer, int actualAnswer) {
        return expectedAnswer == actualAnswer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CsvQuestionEntity> getCsvLines() {
        try {
            URI fileUri = Objects.requireNonNull(getClass().getClassLoader().getResource(String.format("csv/%s", filename)),
            "Файл не найден").toURI();
            Path path = Paths.get(fileUri).toAbsolutePath();
            return new CsvToBeanBuilder(readCsv(path)).withSeparator(';')
                    .withType(CsvQuestionEntity.class).build().parse();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Ошибка в синтаксисе", e);
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
