package ru.otus.hw12.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw12.dao.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
