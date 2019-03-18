package ru.otus.model;

public class User {

    private final String name;
    private final String family;
    private int countRightAnswers;

    public User(String name, String family) {
        this.name = name;
        this.family = family;
        this.countRightAnswers = 0;
    }

    public int getCountRightAnswers() {
        return countRightAnswers;
    }

    public void increaseCountRightAnswers() {
        this.countRightAnswers++;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{name: %s; family; %s; countRightAnswers: %s}", name, family, countRightAnswers);
    }
}
