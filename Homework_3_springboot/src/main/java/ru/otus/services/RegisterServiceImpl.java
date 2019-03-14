package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.model.User;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public User registerUser(String name, String family) {
        return new User(name, family);
    }

}
