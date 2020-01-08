package ru.otus.hw04.service.io;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOStringService implements IOStringService {

    @Override
    public void write(String s, PrintStream ps) {
        ps.println(s);
    }

    @Override
    public String readLine(InputStream is) {
        Scanner scanner = new Scanner(is);
        return scanner.nextLine();
    }
}
