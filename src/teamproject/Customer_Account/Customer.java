package teamproject.Customer_Account;


public class Customer {

	private String name;
	private String emailAddress;
	private String address;
	private String postCode;
	private String telephoneNumber;
	private String fax;
        
	public Customer() {
	}

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

    @Override
    public String toString() {
        return "Customer:\n" + "name=" + name 
                + ",\n emailAddress=" + emailAddress 
                + ",\n address=" + address 
                + ",\n postCode=" + postCode 
                + ",\n telephoneNumber=" + telephoneNumber 
                + ",\n fax=" + fax;
    }  
}