package ru.otus.hw04.service.login;

import org.springframework.stereotype.Service;
import ru.otus.hw04.dao.UserDao;
import ru.otus.hw04.exception.ModuleException;

@Service
public class DefaultLoginService implements LoginService {

    private final UserDao userDao;

    public DefaultLoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username) throws ModuleException {
        User user = userDao.get(username);
        if (user == null) {
            user = new User(username);
            userDao.create(new User(username));
        }
        return user;
    }
}
