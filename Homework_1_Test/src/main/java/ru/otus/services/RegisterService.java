package ru.otus.services;

import ru.otus.model.User;

public interface RegisterService {

    User registerUser(String name, String family);
}
