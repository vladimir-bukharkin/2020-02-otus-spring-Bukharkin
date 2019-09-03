package ru.otus.hw01.service.dataloader;

import ru.otus.hw01.exception.ModuleException;

import java.util.List;

public interface DataLoader<T> {

    List<T> loadAllToList() throws ModuleException;
}
