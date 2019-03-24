package ru.otus.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return countRightAnswers == user.countRightAnswers &&
                Objects.equals(name, user.name) &&
                Objects.equals(family, user.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, countRightAnswers);
    }
}
