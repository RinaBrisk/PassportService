package application.DTO;

public class PassportApplication {

    private Civilian civilian;
    private TypeOfPassport typeOfPassport;

    public PassportApplication(Civilian civilian, TypeOfPassport typeOfPassport){
        this.civilian = civilian;
        this.typeOfPassport = typeOfPassport;
    }

    public Civilian getCivilian() {
        return civilian;
    }

    public TypeOfPassport getTypeOfPassport() {
        return typeOfPassport;
    }
}
