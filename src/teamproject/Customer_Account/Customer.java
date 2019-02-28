package teamproject.Customer_Account;

import java.util.Date;

public class Customer {

	private String name;
	private String emailAddress;
	private String address;
	private String postCode;
	private String telephoneNumber;
	private String fax;
	private Date dateCreated;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return this.postCode;
	}

	/**
	 * 
	 * @param postCode
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/**
	 * 
	 * @param telephoneNumber
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getFax() {
		return this.fax;
	}

	/**
	 * 
	 * @param fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * 
	 * @param dateCreated
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * 
	 * @param vehicle
	 */
	public Vehicle addVehicle(Vehicle vehicle) {
		// TODO - implement Customer.addVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param registrationNumber
	 */
	public void removeVehicle(String registrationNumber) {
		// TODO - implement Customer.removeVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param vehicle
	 */
	public void updateVehicle(Vehicle vehicle) {
		// TODO - implement Customer.updateVehicle
		throw new UnsupportedOperationException();
	}

	public Vehicle[] getVehicle() {
		// TODO - implement Customer.getVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param emailAddress
	 * @param address
	 * @param postCode
	 * @param telephoneNumber
	 * @param fax
	 * @param dateCreated
	 */
	public static Customer Customer(String name, String emailAddress, String address, String postCode, String telephoneNumber, String fax, Date dateCreated) {
		// TODO - implement Customer.Customer
		throw new UnsupportedOperationException();
	}

}