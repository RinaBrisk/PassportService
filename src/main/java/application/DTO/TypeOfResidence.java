package application.DTO;

public enum TypeOfResidence {
    constant(1), temporary(2);
    private int number;

    TypeOfResidence(int number) {
        this.number = number;
    }

    public int getOrdinalNumber() {
        return number;
    }
}