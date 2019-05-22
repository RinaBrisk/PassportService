package application.DTO;

public class Civilian {

    private String FIO;
    private String DateOfBirth;
    private Gender gender;

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFIO() {
        return FIO;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }
}
