package ru.otus.hw10.dao.cascadeoperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.hw10.dao.repository.CommentRepository;

import java.util.Objects;

@Component
public class BookCascadeMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Object> event) {
        handleBookDeleteEvent(event);
        super.onAfterDelete(event);
    }

    private void handleBookDeleteEvent(AfterDeleteEvent<Object> event) {
        if (Objects.equals(event.getCollectionName(), "book")) {
            String id = String.valueOf(event.getSource().get("_id"));
            commentRepository.findAllByBookId(id).forEach(commentRepository::delete);
        }
    }
}
