package application.DTO;

public class ResidenceApplication {

    private int passportID;
    private TypeOfResidence typeOfResidence;
    private Address address;

    public ResidenceApplication(int passport, TypeOfResidence typeOfResidence, Address address){
        this.passportID = passport;
        this.typeOfResidence = typeOfResidence;
        this.address = address;
    }

    public int getPassport() {
        return passportID;
    }

    public int getPassportID() {
        return passportID;
    }

    public Address getAddress() {
        return address;
    }

    public TypeOfResidence getTypeOfResidence() {
        return typeOfResidence;
    }

    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTypeOfResidence(TypeOfResidence typeOfResidence) {
        this.typeOfResidence = typeOfResidence;
    }
}
