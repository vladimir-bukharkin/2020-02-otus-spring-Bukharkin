package ru.otus.hw13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext s = SpringApplication.run(Application.class, args);
//        PasswordEncoder passwordEncoder = s.getBean(PasswordEncoder.class);
//        UserRepository repository = s.getBean(UserRepository.class);
//        repository.save(new User("admin2", passwordEncoder.encode("admin2"), Collections.singletonList("admin")));
    }
}
