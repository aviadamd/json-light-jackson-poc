package jsonReader.person.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonObjectsDto {

    public List<PersonData> personDataList(int iterateOver) {
        List<PersonData> personsData = new ArrayList<>();
        for (int i = 1; i < iterateOver; i++) {
            personsData.add(new PersonData("aviad", "36", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("michal", "35", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("asaf", "9", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("dvir", "6", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("yael", "4", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("aviad", "36", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("michal", "35", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("asaf", "9", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("dvir", "6", new Address("tel aviv", "negba", "3")));
            personsData.add(new PersonData("yael", "4", new Address("tel aviv", "negba", "3")));
        }
        return personsData;
    }

    public PersonData personData() {
        return new PersonData("ella", "23", new Address("ramat gan", "FFF", "7"));
    }


}
