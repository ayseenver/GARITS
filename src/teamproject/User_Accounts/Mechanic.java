package teamproject.User_Accounts;

public class Mechanic extends User {

	private double hourlyRate = 105;

	public double getHourlyRate() {
		return this.hourlyRate;
	}

	/**
	 * 
	 * @param hourlyRate
	 */
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	/**
	 * 
	 * @param hourlyRate
	 */
	public static Mechanic Mechanic(double hourlyRate) {
		// TODO - implement Mechanic.Mechanic
		throw new UnsupportedOperationException();
	}

}