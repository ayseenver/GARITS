package teamproject.Jobs;

public class ActualTask {
	private double actualHour;
	private double actualCost;

        public ActualTask() {
        }
        
	public double getActualHour() {
		return this.actualHour;
	}

	/**
	 * 
	 * @param actualHour
	 */
	public void setActualHour(double actualHour) {
		this.actualHour = actualHour;
	}

	public double getActualCost() {
		return this.actualCost;
	}

	/**
	 * 
	 * @param actualCost
	 */
	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}

}