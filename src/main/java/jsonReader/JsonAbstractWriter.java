package jsonReader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class JsonAbstractWriter<K> {

    Class<K> kClass;
    List<K> objectListType;

    private final Logger logger = getLogger(JsonAbstractWriter.class);

    private final File file;
    private final JsonWriterExtensions jsonWriterExtensions;

    private final ObjectMapper objectMapper = new ObjectMapper();
    public ObjectWriter objectWriter() {
        return this.objectMapper
                .enable(SerializationFeature.INDENT_OUTPUT)
                .writerWithDefaultPrettyPrinter();
    }

    public JsonAbstractWriter(
            List<K> objectListType, Class<K> kClass, File file,
            String fileName, int fileNumber, long jsonFileSizeLimit) {
        this.objectListType = objectListType;
        this.kClass = kClass;
        this.file = file;
        JsonSharedExtensions jsonSharedExtensions = new JsonSharedExtensions();
        FileGenerator createNewFile = jsonSharedExtensions.createNewFile(fileName, fileNumber, jsonFileSizeLimit);
        jsonSharedExtensions.verifyIntention(createNewFile);
        this.jsonWriterExtensions = new JsonWriterExtensions(createNewFile.getFile());
    }

    public JsonAbstractWriter<K> write() {
        this.jsonWriterExtensions.writeToJson(this.objectListType);
        return this;
    }

    public JsonAbstractWriter<K> readAndWrite(List<K> dtoObjectList) {
        try {
            List<K> newDataList = new ArrayList<>();
            List<K> oldDataList = this.readAllJson(this.kClass);
            newDataList.addAll(oldDataList);
            newDataList.addAll(dtoObjectList);
            this.writeToJson(newDataList);
            return this;
        } catch (Exception exception) {
            logger.error("readAndWrite exception" +exception.getMessage());
        }
        return this;
    }

    public List<K> readAllJson(Class<K> dtoTypeClass) {
        try {
            return this.readJsonMapper(dtoTypeClass).readAll();
        } catch (IOException ioException) {
            logger.error("ioException" + ioException.getMessage());
        } catch (ClassCastException castException) {
            logger.error("castException" + castException.getMessage());
        } catch (Exception exception) {
            logger.error("exception" + exception.getMessage());
        }
        return new ArrayList<>();
    }

    public MappingIterator<K> readJsonMapper(Class<K> dtoTypeClass) {
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

    public <T> void writeToJson(List<T> newDataList) {
        try {
            this.objectWriter().writeValue(this.file, newDataList);
        } catch (IOException ioException) {
            logger.error("IOException cannot write to json " + ioException.getMessage());
        }
    }
}
