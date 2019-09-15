package ru.otus.hw02.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;
import ru.otus.hw02.domain.question.Question;
import ru.otus.hw02.exception.IOModuleException;
import ru.otus.hw02.exception.ModuleException;
import ru.otus.hw02.exception.QuestionParsingException;
import ru.otus.hw02.service.i18n.Localization;
import ru.otus.hw02.service.validator.QuestionValidator;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class QuestionCsvDao implements QuestionDao {

    private final Path csvPath;
    private final QuestionValidator questionValidator;

    public QuestionCsvDao(QuestionValidator questionValidator, Localization localization) {
        try {
            this.csvPath = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(localization.getConfig().getQuestionsPath())).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        this.questionValidator = questionValidator;
    }

    @Override
    public List<Question> getAll() throws ModuleException {
        List<Question> result = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(csvPath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                Question question = new Question(parseId(csvRecord),
                        csvRecord.get("question"),
                        csvRecord.get("answer"),
                        csvRecord.get("type"));
                questionValidator.validate(question);
                result.add(question);
            }
        } catch (IOException e) {
            throw new IOModuleException(e);
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new QuestionParsingException(e);
        }
        return result;
    }

    private long parseId(CSVRecord csvRecord) throws QuestionParsingException {
        String id = csvRecord.get("id");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new QuestionParsingException("Parse id to long error. Id value: " + id, e);
        }
    }
}
