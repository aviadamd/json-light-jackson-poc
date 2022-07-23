package jsonReader.blabla;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlaBla {

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String name;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String lastName;

    public BlaBla() {

    }

    public BlaBla(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
