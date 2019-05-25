package application.DTO;

public enum TypeOfPassport {
    domestic(1), foreign(2);
    private int number;

    TypeOfPassport(int number){
        this.number = number;
    }

    public int getOrdinalNumber(){
        return number;
    }
}
