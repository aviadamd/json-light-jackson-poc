package jsonReader;

import jsonReader.person.dto.PersonData;
import jsonReader.person.dto.PersonObjectsDto;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

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
       Mono.just(new JsonReadAndWriteExtensions("aviad.json", 111))
               .log()
               .timeout(Duration.ofMillis(1000))
               .doOnError(error -> logger.info("error write " +error.getMessage()))
               .subscribe(json -> {
                   json.readAndWrite(this.personObjectsDto.personDataList(2), PersonData.class);
                   json.readAndWrite(this.personObjectsDto.personDataList(2), PersonData.class);
               })
               .isDisposed();
    }

    @Test
    @Order(2)
    public void readAndWrite_reactiveCore1() {

    }

}
