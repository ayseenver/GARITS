package teamproject.Customer_Account;

public class Vehicle {

	private String registrationNumber;
	private String make;
	private String model;
	private String engineSerial;
	private String chassisNumber;
	private String colour;
        
        /**
	 * 
	 * @param registrationNumber
	 * @param make
	 * @param model
	 * @param engineSerial
	 * @param chassisNumber
	 * @param colour
	 */
	public Vehicle Vehicle() {
		// TODO - implement Vehicle.Vehicle
		throw new UnsupportedOperationException();
	}

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

    @Override
    public String toString() {
        return "Vehicle:\n" + "registrationNumber=" + registrationNumber 
                + ",\n make=" + make 
                + ",\n model=" + model 
                + ",\n engineSerial=" + engineSerial 
                + ",\n chassisNumber=" + chassisNumber 
                + ",\n colour=" + colour;
    }
   
}