package ru.otus.hw12.exception;

public class DomainObjectNotFoundException extends CoreException{

    public DomainObjectNotFoundException(String tableName, String id) {
        super(tableName + " not found. Id: " + id);
    }
}
