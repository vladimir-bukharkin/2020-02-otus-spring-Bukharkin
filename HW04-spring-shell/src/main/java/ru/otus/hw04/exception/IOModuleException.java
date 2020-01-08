package ru.otus.hw04.exception;

import java.io.IOException;

public class IOModuleException extends ModuleException {

    public IOModuleException(IOException e) {
        super(e);
    }
}
