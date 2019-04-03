package teamproject.Customer_Account;

public class Customer {

    private String ID;
    private String name;
    private String emailAddress;
    private String address;
    private String postCode;
    private String telephoneNumber;
    private String mobileNumber;
    private String fax;
    private String dateCreated;

    public Customer() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "ID: " + ID
                +"\nDate: "+dateCreated
                + "\nName" + name
                + "\nEmailAddress: " + emailAddress
                + "\nAddress:" + address
                + "\nPostCode: " + postCode
                + "\nTel.:" + telephoneNumber
                + "\nMobile: " + mobileNumber
                + "\nfax: " + fax;
    }
}
