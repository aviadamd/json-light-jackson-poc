package jsonReader;

import jsonReader.person.dto.PersonData;
import jsonReader.person.dto.PersonObjectsDto;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonExtensionsPersonTest {

    private PersonObjectsDto personObjectsDto;
    private JsonReadAndWriteExtensions jsonReadAndWriteExtensions;
    private JsonReadAndWriteExtensions jsonReadAndWriteExtensions1;
    private JsonReadAndWriteExtensions jsonReadAndWriteExtensions2;

    @BeforeEach
    public void init() {
        personObjectsDto = new PersonObjectsDto();
        long jsonSizeLimit = 7000;
        jsonReadAndWriteExtensions = new JsonReadAndWriteExtensions("firstVersion.json", 1, jsonSizeLimit);
        jsonReadAndWriteExtensions1 = new JsonReadAndWriteExtensions("secondVersion.json", 2, jsonSizeLimit);
        jsonReadAndWriteExtensions2 = new JsonReadAndWriteExtensions("thirdVersion.json", 3, jsonSizeLimit);
    }

    @Test
    @Order(1)
    @DisplayName("aa_writeToJson_firstVersion")
    public void aa_writeToJson_firstVersion() {
        this.jsonReadAndWriteExtensions
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(2)
    @DisplayName("ab_writeToJson_firstVersion")
    public void ab_writeToJson_firstVersion() {
        this.jsonReadAndWriteExtensions
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(3)
    @DisplayName("ac_writeToJson_firstVersion")
    public void ac_writeToJson_firstVersion() {
        this.jsonReadAndWriteExtensions
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(4)
    @DisplayName("ba_writeToJson_secondVersion")
    public void ba_writeToJson_secondVersion() {
        this.jsonReadAndWriteExtensions1
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(5)
    @DisplayName("bb_writeToJson_secondVersion")
    public void bb_writeToJson_secondVersion() {
        this.jsonReadAndWriteExtensions1
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(6)
    @DisplayName("bc_writeToJson_secondVersion")
    public void bc_writeToJson_secondVersion() {
        this.jsonReadAndWriteExtensions1
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(7)
    @DisplayName("ca_writeToJson_thirdVersion")
    public void ca_writeToJson_thirdVersion() {
        this.jsonReadAndWriteExtensions2
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(8)
    @DisplayName("cb_writeToJson_thirdVersion")
    public void cb_writeToJson_thirdVersion() {
        this.jsonReadAndWriteExtensions2
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }

    @Test
    @Order(9)
    @DisplayName("cc_writeToJson_thirdVersion")
    public void cc_writeToJson_thirdVersion() {
        this.jsonReadAndWriteExtensions2
                .readAndWrite(this.personObjectsDto.personDataList(2000), PersonData.class)
                .readAll(PersonData.class);
    }
}
