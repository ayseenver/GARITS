package teamproject.Reports;

import teamproject.Reports.Report;
import java.util.Date;

public class AverageTimePrice extends Report {

	private String mechanic;

	public void generate() {
		// TODO - implement AverageTimePrice.generate
		throw new UnsupportedOperationException();
	}

	public String getMechanic() {
		return this.mechanic;
	}

	/**
	 * 
	 * @param mechanic
	 */
	public void setMechanic(String mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * 
	 * @param dateFrom
	 * @param dateTill
	 * @param reportDate
	 * @param content
	 */
	public static AverageTimePrice AverageTimePrice(Date dateFrom, Date dateTill, Date reportDate, String content) {
		// TODO - implement AverageTimePrice.AverageTimePrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dateFrom
	 * @param dateTill
	 * @param reportDate
	 * @param content
	 * @param mechanic
	 */
	public static AverageTimePrice AverageTimePrice(Date dateFrom, Date dateTill, Date reportDate, String content, String mechanic) {
		// TODO - implement AverageTimePrice.AverageTimePrice
		throw new UnsupportedOperationException();
	}

}