package ru.otus.hw02.service.io;

import java.util.Scanner;

public class ConsoleIOStringService implements IOStringService {

    @Override
    public void write(String s) {
        System.out.println(s);
    }

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
