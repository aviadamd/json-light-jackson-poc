package jsonReader;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonRegistrationDto {

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private int id;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String path;

    public JsonRegistrationDto() { }

    public JsonRegistrationDto(int id, String path) {
        this.id = id;
        this.path = path;
    }

    @Override
    public String toString() {
        return "JsonRegistrationDto{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
