package jsonReader.person.dto;

public class Address {

    private String city;
    private String address;
    private String floor;

    public Address() {

    }

    public Address(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public Address(String city, String address, String floor) {
        this.city = city;
        this.address = address;
        this.floor = floor;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}
