package teamproject.Customer_Account;

import java.util.Date;

public class Vehicle {

	private String registrationNumber;
	private String make;
	private String model;
	private String engineSerial;
	private String chassisNumber;
	private String colour;
	private Date nextMoTDate;
	private Date nextServiceDate;

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	/**
	 * 
	 * @param registrationNumber
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getMake() {
		return this.make;
	}

	/**
	 * 
	 * @param make
	 */
	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	public String getEngineSerial() {
		return this.engineSerial;
	}

	/**
	 * 
	 * @param engineSerial
	 */
	public void setEngineSerial(String engineSerial) {
		this.engineSerial = engineSerial;
	}

	public String getChassisNumber() {
		return this.chassisNumber;
	}

	/**
	 * 
	 * @param chassisNumber
	 */
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getColour() {
		return this.colour;
	}

	/**
	 * 
	 * @param colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getNextMoTDate() {
		return this.nextMoTDate;
	}

	/**
	 * 
	 * @param nextMoTDate
	 */
	public void setNextMoTDate(Date nextMoTDate) {
		this.nextMoTDate = nextMoTDate;
	}

	public Date getNextServiceDate() {
		return this.nextServiceDate;
	}

	/**
	 * 
	 * @param nextServiceDate
	 */
	public void setNextServiceDate(Date nextServiceDate) {
		this.nextServiceDate = nextServiceDate;
	}

	/**
	 * 
	 * @param registrationNumber
	 * @param make
	 * @param model
	 * @param engineSerial
	 * @param chassisNumber
	 * @param colour
	 * @param nextMoTDate
	 * @param nextServiceDate
	 */
	public Vehicle Vehicle(String registrationNumber, String make, String model, String engineSerial, String chassisNumber, String colour, Date nextMoTDate, Date nextServiceDate) {
		// TODO - implement Vehicle.Vehicle
		throw new UnsupportedOperationException();
	}

}