package teamproject.Customer_Account;

public class Vehicle {

    private String registrationNumber;
    private String make;
    private String model;
    private String engineSerial;
    private String chassisNumber;
    private String colour;
    private String nextMoTDate;
    private String nextServiceDate;

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
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineSerial() {
        return engineSerial;
    }

    public void setEngineSerial(String engineSerial) {
        this.engineSerial = engineSerial;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getNextMoTDate() {
        return nextMoTDate;
    }

    public void setNextMoTDate(String nextMoTDate) {
        this.nextMoTDate = nextMoTDate;
    }

    public String getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(String nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
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
