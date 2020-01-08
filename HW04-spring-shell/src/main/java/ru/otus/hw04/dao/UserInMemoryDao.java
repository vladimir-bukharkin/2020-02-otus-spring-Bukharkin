package ru.otus.hw04.dao;

import org.springframework.stereotype.Repository;
import ru.otus.hw04.exception.DataException;
import ru.otus.hw04.service.login.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserInMemoryDao implements UserDao {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void create(User user) throws DataException {
        if (users.containsKey(user.getName())) {
            throw new DataException("User already exists");
        }
        users.put(user.getName(), user);
    }

    @Override
    public void remove(User user) {
        users.remove(user.getName());
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }
}
