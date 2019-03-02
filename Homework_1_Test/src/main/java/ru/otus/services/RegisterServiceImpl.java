package ru.otus.services;

import ru.otus.model.User;

public class RegisterServiceImpl implements RegisterService {

    @Override
    public User registerUser(String name, String family) {
        return new User(name, family);
    }

}
