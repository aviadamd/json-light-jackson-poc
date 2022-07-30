package jsonReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderExtensions {

    private final Logger logger = LoggerFactory.getLogger(JsonReaderExtensions.class);

    private final File file;
    private final ObjectMapper objectMapper;

    public JsonReaderExtensions(File file) {
        this.file = file;
        this.objectMapper = new ObjectMapper();
    }

    public <T> List<T> readAndReturnJsonListOf(Class<T> dtoTypeClass) {
        List<T> newDataList = new ArrayList<>();

        try {
            if (this.file.exists()) {
                String name = this.file.getName();
                List<T> oldDataList = this.readAllJson(dtoTypeClass);
                if (!oldDataList.isEmpty()) {
                    newDataList.addAll(oldDataList);
                    logger.debug("file " + name + " has updated");
                }
                return newDataList;
            }
        } catch (Exception ioException) {
            logger.error("Exception cannot access " + ioException.getMessage());
        }

        return newDataList;
    }

    public <T> List<T> readAllJson(Class<T> dtoTypeClass) {
        try {
            return this.readJson(dtoTypeClass).readAll();
        } catch (IOException ioException) {
            logger.error("ioException" + ioException.getMessage());
        } catch (ClassCastException castException) {
            logger.error("castException" + castException.getMessage());
        } catch (Exception exception) {
            logger.error("exception" + exception.getMessage());
        }
        return new ArrayList<>();
    }

    public <T> MappingIterator<T> readJson(Class<T> dtoTypeClass) {
        try {
            if (this.file.exists()) {
                ObjectReader reader = this.objectMapper.readerFor(dtoTypeClass);
                reader.createParser(this.file)
                        .enable(JsonParser.Feature.ALLOW_COMMENTS)
                        .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
                return reader.readValues(this.file);
            }
        } catch (IOException ioException) {
            logger.error("ioException" + ioException.getMessage());
        } catch (Exception exception) {
            logger.error("exception" + exception.getMessage());
        }
        return MappingIterator.emptyIterator();
    }
}
