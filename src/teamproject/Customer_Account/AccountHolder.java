package teamproject.Customer_Account;

public class AccountHolder extends Customer {

	private boolean payLaterConfigured = false;

	public boolean getPayLaterConfigured() {
		return this.payLaterConfigured;
	}

	/**
	 * 
	 * @param payLaterConfigured
	 */
	public void setPayLaterConfigured(boolean payLaterConfigured) {
		this.payLaterConfigured = payLaterConfigured;
	}

	/**
	 * 
	 * @param customer
	 * @param payLaterConfigured
	 */
	public static AccountHolder AccountHolder(Customer customer, boolean payLaterConfigured) {
		// TODO - implement AccountHolder.AccountHolder
		throw new UnsupportedOperationException();
	}

}