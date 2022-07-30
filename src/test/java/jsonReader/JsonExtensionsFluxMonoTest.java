package jsonReader;

import jsonReader.person.dto.PersonData;
import jsonReader.person.dto.PersonObjectsDto;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonExtensionsFluxMonoTest {

    private PersonObjectsDto personObjectsDto;
    private final Logger logger = LoggerFactory.getLogger(JsonExtensionsFluxMonoTest.class);

    @BeforeEach
    public void init() {
        personObjectsDto = new PersonObjectsDto();
    }

    @Test
    @Order(1)
    public void readAndWrite_reactiveCore() {
       Flux.just(new JsonReadAndWriteExtensions("aviad.json", 111))
               .log(logger.getName())
               .subscribe(json -> {
                   json.readAndWrite(this.personObjectsDto.personDataList(2), PersonData.class);
                   json.readAndWrite(this.personObjectsDto.personDataList(2), PersonData.class);
               })
               .isDisposed();
    }

}
