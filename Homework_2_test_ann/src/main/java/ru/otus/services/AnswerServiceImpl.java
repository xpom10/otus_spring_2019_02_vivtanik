package ru.otus.services;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.csvModel.CsvQuestionEntity;
import ru.otus.messageSystem.MessageSystem;

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
    private MessageSystem messageSystem;

    public AnswerServiceImpl(String filename, MessageSystem messageSystem) {
        this.filename = filename;
        this.messageSystem = messageSystem;
    }

    @Override
    public boolean checkAnswer(int expectedAnswer, int actualAnswer) {
        return expectedAnswer == actualAnswer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CsvQuestionEntity> getCsvLines() {
        try {
            String resourcePath = String.format("csv/%s/%s", messageSystem.getLocale().getLanguage(), filename);
            URI fileUri = Objects.requireNonNull(getClass().getClassLoader()
                            .getResource(resourcePath),
                    messageSystem.getMessage("file.not.found", new Object[]{resourcePath})).toURI();
            Path path = Paths.get(fileUri).toAbsolutePath();
            return new CsvToBeanBuilder(readCsv(path)).withSeparator(';')
                    .withType(CsvQuestionEntity.class).build().parse();
        } catch (URISyntaxException e) {
            throw new RuntimeException(messageSystem.getMessage("syntax.err", null), e);
        }
    }

    private FileReader readCsv(Path path) {
        try {
            return new FileReader(path.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(messageSystem.getMessage("file.not.found", new Object[]{path.toString()}), e);
        }
    }
}
