package ru.otus.csvModel;

import com.opencsv.bean.CsvBindByName;

public class CsvQuestionEntity {

    @CsvBindByName(column = "Question")
    private String question;

    @CsvBindByName(column = "Answer1")
    private String answer1;

    @CsvBindByName(column = "Answer2")
    private String answer2;

    @CsvBindByName(column = "Answer3")
    private String answer3;

    @CsvBindByName(column = "RightAnswer")
    private int rightAnswer;



    public String getAnswers() {
        return String.format("1. %s; 2. %s; 3. %s;", answer1, answer2, answer3);
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public String getQuestion() {
        return question;
    }
}
