package Spare_Parts;

public class SparePart {

	private String partID;
	private String partName;
	private String vehicleType;
	private int year;
	private double costPrice;
	private double sellingPrice = costPrice * markup;
	private int quantity;
	private int threshold = 10;
	private static float markup = (float) 1.3;

	public void alert() {
		// TODO - implement SparePart.alert
		throw new UnsupportedOperationException();
	}

	public String getPartID() {
		return this.partID;
	}

	/**
	 * 
	 * @param partID
	 */
	public void setPartID(String partID) {
		this.partID = partID;
	}

	public String getName() {
		// TODO - implement SparePart.getName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		// TODO - implement SparePart.setName
		throw new UnsupportedOperationException();
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	/**
	 * 
	 * @param vehicleType
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getYear() {
		return this.year;
	}

	/**
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	public double getCostPrice() {
		return this.costPrice;
	}

	/**
	 * 
	 * @param costPrice
	 */
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * 
	 * @param sellingPrice
	 */
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getType() {
		// TODO - implement SparePart.getType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		// TODO - implement SparePart.setType
		throw new UnsupportedOperationException();
	}

	public int getThreshold() {
		return this.threshold;
	}

	/**
	 * 
	 * @param threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * 
	 * @param partID
	 * @param name
	 * @param vehicleType
	 * @param year
	 * @param costPrice
	 * @param quantity
	 */
	public static SparePart SparePart(String partID, String name, String vehicleType, int year, double costPrice, int quantity) {
		// TODO - implement SparePart.SparePart
		throw new UnsupportedOperationException();
	}

}