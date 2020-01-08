package ru.otus.hw03.service.io;

import java.io.InputStream;
import java.io.PrintStream;

public interface IOStringService {

    void write(String s, PrintStream ps);

    String readLine(InputStream is);
}
