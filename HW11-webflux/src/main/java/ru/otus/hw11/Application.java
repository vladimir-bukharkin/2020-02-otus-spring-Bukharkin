package ru.otus.hw11;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.hw11.dao.mapper.LibraryMapper;
import ru.otus.hw11.dao.repository.BookRepository;
import ru.otus.hw11.dao.repository.CommentRepository;
import ru.otus.hw11.dto.Book;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@EnableMongoRepositories
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(BookRepository bookRepository, CommentRepository commentRepository, LibraryMapper libraryMapper) {
        return route()
                .GET("/func/book", queryParam("name", StringUtils::isNotEmpty),
                        request -> request.queryParam("name")
                                .map(s-> bookRepository.findAllByName(s).map(libraryMapper::map))
                                .map(persons -> ok().body(persons, Book.class))
                                .orElse(notFound().build())
                )
                .GET("/func/book", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(bookRepository.findAll()
                                .map(libraryMapper::map), Book.class))
                .build();
    }
}
