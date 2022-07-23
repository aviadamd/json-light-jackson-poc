package jsonReader;

import jsonReader.blabla.BlaBla;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonExtensionsTest {

    private JsonWriterExtensions jsonWriterExtensions;

    @BeforeEach
    public void init() {
        this.jsonWriterExtensions = new JsonWriterExtensions(new File("newPerson.json"));
    }

    @Test
    @Order(5)
    public void writeToJson_jsonWriter() {
        List<BlaBla> blaBlas = new ArrayList<>(Arrays.asList(new BlaBla("aviad","ben shemon"), new BlaBla("michal","anzarut")));
        jsonWriterExtensions.readAndWrite(blaBlas, BlaBla.class);
    }
}
