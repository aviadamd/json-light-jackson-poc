package jsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonWriterExtensions {

    private final Logger logger = LoggerFactory.getLogger(JsonWriterExtensions.class);

    private final File file;
    private final ObjectMapper objectMapper;

    public JsonWriterExtensions(File file) {
        this.file = file;
        this.objectMapper = new ObjectMapper();
    }

    public ObjectWriter objectWriter() {
        return this.objectMapper
                .enable(SerializationFeature.INDENT_OUTPUT)
                .writerWithDefaultPrettyPrinter();
    }

    public <T> JsonWriterExtensions readAndWrite(T dtoObjectList, Class<T> dtoTypeClass) {
        return this.readAndWrite(
                Collections.singletonList(dtoObjectList),
                dtoTypeClass
        );
    }

    public <T> JsonWriterExtensions readAndWrite(List<T> dtoObjectList, Class<T> dtoTypeClass) {
        try {

            List<T> newDataList = new ArrayList<>();
            JsonReaderExtensions jsonReaderExtensions = new JsonReaderExtensions(this.file);
            newDataList.addAll(jsonReaderExtensions.readAndReturnJsonListOf(dtoTypeClass));
            newDataList.addAll(dtoObjectList);
            this.writeToJson(newDataList);
            return this;

        } catch (Exception exception) {
            logger.error("readAndWrite exception" +exception.getMessage());
        }

        return this;
    }

    public <T> void writeToJson(T newData) {
        try {
            this.objectWriter().writeValue(this.file, newData);
        } catch (IOException ioException) {
            logger.error("IOException cannot write to json " + ioException.getMessage());
        }
    }

    public <T> void writeToJson(List<T> newDataList) {
        try {
            this.objectWriter().writeValue(this.file, newDataList);
        } catch (IOException ioException) {
            logger.error("IOException cannot write to json " + ioException.getMessage());
        }
    }

    public <T> void writeValuesFromListAsString(List<T> type) {
        try {
            List<String> collector = new ArrayList<>();
            for (T t: type) collector.add(objectMapper.writeValueAsString(t));
            this.objectWriter().writeValue(this.file, collector);
        } catch (IOException ioException) {
            logger.error("IOException cannot write to json " + ioException.getMessage());
        }
    }
}