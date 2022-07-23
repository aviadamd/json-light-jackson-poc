package jsonReader.person.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonData {

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String name;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String age;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private Address address;

    public PersonData() {

    }

    public PersonData(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public PersonData(String name, String age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }
    public void setAddress(Address address) { this.address = address; }
    public Address getAddress() { return address; }

    @Override
    public String toString() {
        return "PersonData{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address +
                '}';
    }
}
