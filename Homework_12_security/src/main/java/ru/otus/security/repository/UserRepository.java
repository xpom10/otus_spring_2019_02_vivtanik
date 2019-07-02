package ru.otus.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.security.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
