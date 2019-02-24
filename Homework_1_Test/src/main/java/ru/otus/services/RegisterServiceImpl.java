package ru.otus.services;

import ru.otus.model.User;

public class RegisterServiceImpl implements RegisterService {

    private User user;

    @Override
    public User registerUser(String name, String family) {
        this.user = new User(name, family);
        return user;
    }

}
