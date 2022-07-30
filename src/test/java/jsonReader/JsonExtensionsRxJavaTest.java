package jsonReader;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jsonReader.person.dto.PersonData;
import jsonReader.person.dto.PersonObjectsDto;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonExtensionsRxJavaTest {

    private PersonObjectsDto personObjectsDto;
    private CompositeDisposable compositeDisposable;
    private final Logger logger = LoggerFactory.getLogger(JsonExtensionsRxJavaTest.class);

    @BeforeEach
    public void init() {
        personObjectsDto = new PersonObjectsDto();
        this.compositeDisposable = new CompositeDisposable();
    }

    private @NonNull Single<JsonReadAndWriteExtensions> readAnd() {
        return Single.just(new JsonReadAndWriteExtensions("myjson.json",11));
    }

    @Test
    @Order(1)
    public void readAndWrite_rxJava_observable() {
        Observable<JsonReadAndWriteExtensions> observable = Observable
                .just(new JsonReadAndWriteExtensions("myjson.json",11))
                .doOnError(e -> logger.error("error " + e.getMessage()))
                .doOnNext(json -> {
                    json.readAndWrite(this.personObjectsDto.personDataList(2), PersonData.class);
                    json.readAndWrite(this.personObjectsDto.personDataList(2), PersonData.class);
                });
        observable.subscribe();
    }

    @Test
    @Order(2)
    public void readAndWrite_rxJava_compositeDisposable() {
        this.compositeDisposable.add(
                Single.just(new JsonReadAndWriteExtensions("jjjj.json",5)
                ).subscribeWith(new DisposableSingleObserver<JsonReadAndWriteExtensions>() {
                    @Override
                    public void onSuccess(@NonNull JsonReadAndWriteExtensions json) {
                        json.readAndWrite(new PersonData("aviad","44"), PersonData.class);
                        logger.info("writeToJson_jsonWriter pass");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        logger.error("writeToJson_jsonWriter " + e.getMessage());
                    }
                }));
        compositeDisposable.isDisposed();
    }
}
