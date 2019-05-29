package application.DTO;

public class Address {

    private String city;
    private String street;
    private String house;
    private String apartment;

    public Address(String city, String street, String  home, String apartment){
        this.city = city;
        this.street = street;
        this.house = home;
        this.apartment = apartment;
    }

    public Address(String city, String street, String home){
        this.city = city;
        this.street = street;
        this.house = home;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getApartment() {
        return apartment;
    }
}
