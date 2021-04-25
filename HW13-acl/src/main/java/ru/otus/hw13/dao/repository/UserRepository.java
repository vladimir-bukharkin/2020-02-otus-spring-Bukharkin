package ru.otus.hw13.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw13.dao.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
