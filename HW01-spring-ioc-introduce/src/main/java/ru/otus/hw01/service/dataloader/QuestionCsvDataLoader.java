package ru.otus.hw01.service.dataloader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.exception.DataException;
import ru.otus.hw01.exception.IOModuleException;
import ru.otus.hw01.exception.ModuleException;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionCsvDataLoader implements QuestionDataLoader {

    private final Path csvPath;

    public QuestionCsvDataLoader(String csvPath) {
        try {
            this.csvPath = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(csvPath)).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> loadAllToList() throws ModuleException {
        List<Question> result = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(csvPath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                Question question = new Question(parseId(csvRecord),
                        csvRecord.get("question"),
                        csvRecord.get("answer"),
                        csvRecord.get("type"));
                result.add(question);
            }
        } catch (IOException e) {
            throw new IOModuleException(e);
        }
        return result;
    }

    private long parseId(CSVRecord csvRecord) throws DataException {
        String id = csvRecord.get("id");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new DataException("Parse id error. Id value: " + id, e);
        }
    }
}
