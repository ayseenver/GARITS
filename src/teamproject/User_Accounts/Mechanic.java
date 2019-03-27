package teamproject.User_Accounts;

public class Mechanic extends User {

    private double hourlyRate;

    public Mechanic(User u) {
        super(u);
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}
