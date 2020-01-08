package ru.otus.hw04.dao;

import org.springframework.stereotype.Repository;
import ru.otus.hw04.service.login.User;
import ru.otus.hw04.service.statistic.Statistic;

import java.util.*;

@Repository
public class StatisticInMemoryDao implements StatisticDao {

    private final Map<String, Statistic> userStatistics = new HashMap<>();

    @Override
    public Statistic getLastForUser(String username) {
        return userStatistics.get(username);
    }

    @Override
    public void save(User user, Statistic statistic) {
        userStatistics.put(user.getName(), statistic);
    }

    @Override
    public Collection<Statistic> getAll() {
        return userStatistics.values();
    }
}
