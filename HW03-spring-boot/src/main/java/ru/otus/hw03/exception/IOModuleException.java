package ru.otus.hw03.exception;

import java.io.IOException;

public class IOModuleException extends ModuleException {

    public IOModuleException(IOException e) {
        super(e);
    }
}
