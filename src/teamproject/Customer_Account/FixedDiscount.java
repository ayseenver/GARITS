package teamproject.Customer_Account;

public class FixedDiscount extends DiscountPlan {

	private float percentage;

	public float getPercentage() {
		return this.percentage;
	}

	/**
	 * 
	 * @param percentage
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	/**
	 * 
	 * @param percentage
	 */
	public static FixedDiscount FixedDiscount(float percentage) {
		// TODO - implement FixedDiscount.FixedDiscount
		throw new UnsupportedOperationException();
	}

}