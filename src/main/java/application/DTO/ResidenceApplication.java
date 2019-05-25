package application.DTO;

public class ResidenceApplication {

    private Passport passport;
    private TypeOfResidence typeOfResidence;

    ResidenceApplication(Passport passport, TypeOfResidence typeOfResidence){
        this.passport = passport;
        this.typeOfResidence = typeOfResidence;
    }

    public Passport getPassport() {
        return passport;
    }

    public TypeOfResidence getTypeOfResidence() {
        return typeOfResidence;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setTypeOfResidence(TypeOfResidence typeOfResidence) {
        this.typeOfResidence = typeOfResidence;
    }
}
