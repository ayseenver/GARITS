package teamproject.Jobs;

public class RepairBay {

	private int bayNumber;
	private boolean inUse;

	public int getBayNumber() {
		return this.bayNumber;
	}

	/**
	 * 
	 * @param bayNumber
	 */
	public void setBayNumber(int bayNumber) {
		this.bayNumber = bayNumber;
	}

	public boolean getInUse() {
		return this.inUse;
	}

	/**
	 * 
	 * @param inUse
	 */
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	/**
	 * 
	 * @param bayNumber
	 * @param inUse
	 */
	public static RepairBay RepairBay(int bayNumber, boolean inUse) {
		// TODO - implement RepairBay.RepairBay
		throw new UnsupportedOperationException();
	}

}