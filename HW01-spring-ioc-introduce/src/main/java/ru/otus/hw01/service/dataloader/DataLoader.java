package ru.otus.hw01.service.dataloader;

import java.util.List;

public interface DataLoader<T> {

    List<T> loadAllToList();
}
