package jsonReader;

import com.fasterxml.jackson.databind.MappingIterator;
import io.reactivex.rxjava3.core.Single;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JsonReadAndWriteExtensions {

    private JsonReaderExtensions jsonReaderExtensions;
    private JsonWriterExtensions jsonWriterExtensions;

    public Single<JsonReadAndWriteExtensions> readAndWriteExtensions(String fileName, int fileNum) {
        return Single.just(new JsonReadAndWriteExtensions(fileName, fileNum));
    }

    public JsonReadAndWriteExtensions(String fileName, int fileNumber) {
        this(fileName, fileNumber, 6000);
    }

    public JsonReadAndWriteExtensions(String fileName, int fileNumber, long jsonFileSizeLimit) {
        JsonSharedExtensions jsonSharedExtensions = new JsonSharedExtensions();
        FileGenerator createNewFile = jsonSharedExtensions.createNewFile(fileName, fileNumber, jsonFileSizeLimit);
        jsonSharedExtensions.verifyIntention(createNewFile);
        this.jsonWriterExtensions = new JsonWriterExtensions(createNewFile.getFile());
        this.jsonReaderExtensions = new JsonReaderExtensions(createNewFile.getFile());
    }

    public <V> JsonReadAndWriteExtensions write(V dtoObject) {
        this.jsonWriterExtensions.writeToJson(dtoObject);
        return this;
    }

    public <V> JsonReadAndWriteExtensions write(List<V> dtoListObject) {
        this.jsonWriterExtensions.writeToJson(dtoListObject);
        return this;
    }

    public <V> JsonReadAndWriteExtensions readAndWrite(V dtoObject, Class<V> dtoTypeClass) {
        this.jsonWriterExtensions.readAndWrite(dtoObject, dtoTypeClass);
        return this;
    }

    public <V> JsonReadAndWriteExtensions readAndWrite(List<V> dtoObjectList, Class<V> dtoTypeClass) {
        this.jsonWriterExtensions.readAndWrite(dtoObjectList, dtoTypeClass);
        return this;
    }

    public <V> List<V> readAndReturnJsonListOf(Class<V> dtoTypeClass) {
        return this.jsonReaderExtensions.readAndReturnJsonListOf(dtoTypeClass);
    }

    public <V> List<V> readAll(Class<V> dtoTypeClass) {
        return this.jsonReaderExtensions.readAllJson(dtoTypeClass);
    }

    public <V>MappingIterator<V> read(Class<V> dtoTypeClass) {
        return this.jsonReaderExtensions.readJson(dtoTypeClass);
    }

    public <L> List<L> filterBy(Class<L> dtoObject, Predicate<L> filterBy) {
        return this.readAll(dtoObject)
                .stream()
                .filter(filterBy)
                .collect(Collectors.toList());
    }
}
