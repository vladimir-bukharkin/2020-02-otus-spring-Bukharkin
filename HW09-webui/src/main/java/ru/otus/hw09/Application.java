package ru.otus.hw09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        var s = SpringApplication.run(Application.class, args);
    }
}
