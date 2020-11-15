package ru.otus.hw04.dao;

import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.service.login.User;
import ru.otus.hw04.service.statistic.Statistic;

public interface StatisticDao extends Dao<Statistic>{

    Statistic getLastForUser(String username) throws ModuleException;

    void save(User user, Statistic statistic) throws ModuleException;
}
