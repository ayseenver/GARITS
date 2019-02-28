package teamproject.Jobs;

import teamproject.Jobs.Task;

public class ActualTask {

	private double actualHour;
	private double actualCost;

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

	/**
	 * 
	 * @param task
	 * @param actualHour
	 * @param actualCost
	 */
	public static ActualTask ActualTask(Task task, double actualHour, double actualCost) {
		// TODO - implement ActualTask.ActualTask
		throw new UnsupportedOperationException();
	}

}