package application.DTO;

public class Passport {

    private TypeOfPassport typeOfPassport;
    private Civilian civilian;
    private int series;
    private int number;

    public Passport(TypeOfPassport typeOfPassport, Civilian civilian){
        this.typeOfPassport = typeOfPassport;
        this.civilian = civilian;
    }

    public TypeOfPassport getTypeOfPassport() {
        return typeOfPassport;
    }

    public Civilian getCivilian() {
        return civilian;
    }

    public int getSeries() {
        return series;
    }

    public int getNumber() {
        return number;
    }

    public void setTypeOfPassport(TypeOfPassport typeOfPassport) {
        this.typeOfPassport = typeOfPassport;
    }

    public void setCivilian(Civilian civilian) {
        this.civilian = civilian;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
