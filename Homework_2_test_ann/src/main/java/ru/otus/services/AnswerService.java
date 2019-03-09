package ru.otus.services;

import ru.otus.csvModel.CsvQuestionEntity;

import java.util.List;

public interface AnswerService {

    boolean checkAnswer(int expectedAnswer, int actualAnswer);

    List<CsvQuestionEntity> getCsvLines();


}
