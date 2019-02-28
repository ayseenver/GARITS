package teamproject.Customer_Account;

import teamproject.Jobs.Task;
import java.util.Date;

public class CustomerController {

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
	public Customer createCustomer(String name, String emailAddress, String address, String postCode, String telephoneNumber, String fax, Date dateCreated) {
		// TODO - implement CustomerController.createCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param registrationNumber
	 * @param make
	 * @param model
	 * @param engineSerial
	 * @param chassisNumber
	 * @param colour
	 * @param nextMoTDate
	 * @param nextServiceDate
	 */
	public Vehicle createCustomerVehicle(String customerName, String emailAddress, String registrationNumber, String make, String model, String engineSerial, String chassisNumber, String colour, Date nextMoTDate, Date nextServiceDate) {
		// TODO - implement CustomerController.createCustomerVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param vehicleRegistrationNumber
	 */
	public void removeCustomerVehicle(String customerName, String emailAddress, String vehicleRegistrationNumber) {
		// TODO - implement CustomerController.removeCustomerVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param emailAddress
	 */
	public void removeCustomer(String name, String emailAddress) {
		// TODO - implement CustomerController.removeCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param emailAddress
	 */
	public Customer getCustomer(String name, String emailAddress) {
		// TODO - implement CustomerController.getCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param vehicleRegistrationNumber
	 */
	public Vehicle getVehicle(String vehicleRegistrationNumber) {
		// TODO - implement CustomerController.getVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param customerdetails
	 */
	public Void updateCustomerDetails(String customerName, String emailAddress, Customer customerdetails) {
		// TODO - implement CustomerController.updateCustomerDetails
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param vehicle
	 */
	public Void updateCustomerVehicleDetails(String customerName, String emailAddress, Vehicle vehicle) {
		// TODO - implement CustomerController.updateCustomerVehicleDetails
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param payLaterOption
	 */
	public void configurePayLater(String customerName, String emailAddress, boolean payLaterOption) {
		// TODO - implement CustomerController.configurePayLater
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param orderedValueThisMonth
	 * @param applicableDiscount
	 * @param valueToBeDeducted
	 */
	public FlexibleDiscount setDiscountPlanToFlexibleDiscount(String customerName, String emailAddress, double orderedValueThisMonth, double applicableDiscount, double valueToBeDeducted) {
		// TODO - implement CustomerController.setDiscountPlanToFlexibleDiscount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param percentage
	 */
	public FixedDiscount setDiscountPlanToFixedDiscount(String customerName, String emailAddress, float percentage) {
		// TODO - implement CustomerController.setDiscountPlanToFixedDiscount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param MoTPercentage
	 * @param sparePartsPercentage
	 * @param servicePercentage
	 */
	public VariableDiscount setDiscountPlanToVariableDiscount(String customerName, String emailAddress, float MoTPercentage, float sparePartsPercentage, float servicePercentage) {
		// TODO - implement CustomerController.setDiscountPlanToVariableDiscount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 */
	public boolean getPayLaterConfigured(String customerName, String emailAddress) {
		// TODO - implement CustomerController.getPayLaterConfigured
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param payLaterConfigured
	 */
	public void setPayLaterConfigured(String customerName, String emailAddress, boolean payLaterConfigured) {
		// TODO - implement CustomerController.setPayLaterConfigured
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customer
	 * @param payLaterConfigured
	 */
	public AccountHolder createAccountHolder(Customer customer, boolean payLaterConfigured) {
		// TODO - implement CustomerController.createAccountHolder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param task
	 * @param percentage
	 */
	public TaskDiscount setVariableDiscountSpecificTask(String customerName, String emailAddress, Task task, float percentage) {
		// TODO - implement CustomerController.setVariableDiscountSpecificTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 */
	public TaskDiscount[] getVariableDiscountTask(String customerName, String emailAddress) {
		// TODO - implement CustomerController.getVariableDiscountTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param taskID
	 */
	public TaskDiscount getVariableDiscountSpecificTask(String customerName, String emailAddress, int taskID) {
		// TODO - implement CustomerController.getVariableDiscountSpecificTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 */
	public FlexibleDiscount getFlexibleDiscount(String customerName, String emailAddress) {
		// TODO - implement CustomerController.getFlexibleDiscount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 */
	public FixedDiscount getFixedDiscount(String customerName, String emailAddress) {
		// TODO - implement CustomerController.getFixedDiscount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 */
	public VariableDiscount getVaribleDiscount(String customerName, String emailAddress) {
		// TODO - implement CustomerController.getVaribleDiscount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param amount
	 */
	public void sendCheque(String customerName, String emailAddress, double amount) {
		// TODO - implement CustomerController.sendCheque
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerName
	 * @param emailAddress
	 * @param amount
	 */
	public void deductFromOrder(String customerName, String emailAddress, double amount) {
		// TODO - implement CustomerController.deductFromOrder
		throw new UnsupportedOperationException();
	}

	public static CustomerController CustomerController() {
		// TODO - implement CustomerController.CustomerController
		throw new UnsupportedOperationException();
	}

}