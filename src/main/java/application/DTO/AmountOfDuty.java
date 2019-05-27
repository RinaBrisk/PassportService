package application.DTO;

public enum AmountOfDuty {
    domesticDuty(200),
    foreignDuty(3400);

    private int amount;

    AmountOfDuty(int amount){
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

}
