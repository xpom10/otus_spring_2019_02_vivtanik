package ru.otus.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.security.domain.LibraryUser;

public interface UserRepository extends MongoRepository<LibraryUser, String> {
}
