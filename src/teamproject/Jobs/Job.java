package teamproject.Jobs;

import teamproject.User_Accounts.Mechanic;
import Spare_Parts.SparePart;
import java.util.Date;
import teamproject.Customer_Account.Vehicle;
import teamproject.Jobs.SparePartUsed;
import teamproject.Jobs.Task;

public class Job {

	Mechanic allocates;
	private int jobID;
	private Date dateBookedIn;
	private String status;
	private String type;

	public int getJobID() {
		return this.jobID;
	}

	/**
	 * 
	 * @param jobID
	 */
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public Date getDateBookedIn() {
		return this.dateBookedIn;
	}

	/**
	 * 
	 * @param dateBookedIn
	 */
	public void setDateBookedIn(Date dateBookedIn) {
		this.dateBookedIn = dateBookedIn;
	}

	public String getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @param task
	 */
	public EstimatedTask addEstimatedTask(Task task) {
		// TODO - implement Job.addEstimatedTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param taskID
	 */
	public void removeEstimatedTask(int taskID) {
		// TODO - implement Job.removeEstimatedTask
		throw new UnsupportedOperationException();
	}

	public EstimatedTask[] getEstimatedTask() {
		// TODO - implement Job.getEstimatedTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param taskID
	 */
	public void removeActualTask(int taskID) {
		// TODO - implement Job.removeActualTask
		throw new UnsupportedOperationException();
	}

	public ActualTask[] getActualTask() {
		// TODO - implement Job.getActualTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param task
	 * @param actualHour
	 * @param actualCost
	 */
	public ActualTask addActualTask(Task task, double actualHour, double actualCost) {
		// TODO - implement Job.addActualTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param vehicle
	 */
	public Vehicle setVehicle(Vehicle vehicle) {
		// TODO - implement Job.setVehicle
		throw new UnsupportedOperationException();
	}

	public Vehicle getVehicle() {
		// TODO - implement Job.getVehicle
		throw new UnsupportedOperationException();
	}

	public void removeAllocatedVehicle() {
		// TODO - implement Job.removeAllocatedVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sparePart
	 */
	public SparePartUsed addSparePartUsed(SparePart sparePart) {
		// TODO - implement Job.addSparePartUsed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sparePartID
	 */
	public void removeSparePartUsed(String sparePartID) {
		// TODO - implement Job.removeSparePartUsed
		throw new UnsupportedOperationException();
	}

	public SparePartUsed[] getSparePartUsed() {
		// TODO - implement Job.getSparePartUsed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param dataBookedIn
	 * @param status
	 * @param type
	 */
	public static Job Job(int jobID, Date dataBookedIn, String status, String type) {
		// TODO - implement Job.Job
		throw new UnsupportedOperationException();
	}

}