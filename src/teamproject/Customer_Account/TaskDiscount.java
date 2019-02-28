package teamproject.Customer_Account;

import teamproject.Jobs.Task;

public class TaskDiscount {

	private double taskDiscountPercentage;

	public double getTaskDiscountPercentage() {
		return this.taskDiscountPercentage;
	}

	/**
	 * 
	 * @param taskDiscountPercentage
	 */
	public void setTaskDiscountPercentage(double taskDiscountPercentage) {
		this.taskDiscountPercentage = taskDiscountPercentage;
	}

	/**
	 * 
	 * @param task
	 * @param taskDiscountPercentage
	 */
	public static TaskDiscount TaskDiscount(Task task, double taskDiscountPercentage) {
		// TODO - implement TaskDiscount.TaskDiscount
		throw new UnsupportedOperationException();
	}

}