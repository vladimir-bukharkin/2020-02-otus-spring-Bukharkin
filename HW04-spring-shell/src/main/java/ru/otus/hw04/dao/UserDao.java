package ru.otus.hw04.dao;

import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.service.login.User;

public interface UserDao extends Dao<User> {

    void create(User user) throws ModuleException;

    void remove(User user) throws ModuleException;

    User get(String username) throws ModuleException;
}
