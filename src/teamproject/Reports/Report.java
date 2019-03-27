package teamproject.Reports;

import java.util.Date;

public abstract class Report {

	private int dateFrom;
	private int dateTill;
	private int reportDate;
	private String content;

	public void print() {
		// TODO - implement Report.print
		throw new UnsupportedOperationException();
	}

	public abstract void generate();

	public Date getDateFrom() {
		// TODO - implement Report.getDateFrom
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dateFrom
	 */
	public void setDateFrom(Date dateFrom) {
		// TODO - implement Report.setDateFrom
		throw new UnsupportedOperationException();
	}

	public Date getDateTill() {
		// TODO - implement Report.getDateTill
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dateTill
	 */
	public void setDateTill(Date dateTill) {
		// TODO - implement Report.setDateTill
		throw new UnsupportedOperationException();
	}

	public Date getReportDate() {
		// TODO - implement Report.getReportDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reportDate
	 */
	public void setReportDate(Date reportDate) {
		// TODO - implement Report.setReportDate
		throw new UnsupportedOperationException();
	}

	public String getContent() {
		return this.content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @param dateFrom
	 * @param dateTill
	 * @param reportDate
	 * @param content
	 */
	public static Report Report(Date dateFrom, Date dateTill, Date reportDate, String content) {
		// TODO - implement Report.Report
		throw new UnsupportedOperationException();
	}

}