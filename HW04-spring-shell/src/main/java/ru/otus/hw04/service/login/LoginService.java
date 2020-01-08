package ru.otus.hw04.service.login;

import ru.otus.hw04.exception.ModuleException;

public interface LoginService {

    User login(String username) throws ModuleException;
}
