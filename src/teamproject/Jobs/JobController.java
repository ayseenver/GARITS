package teamproject.Jobs;

import teamproject.User_Accounts.Mechanic;
import java.util.*;
import teamproject.Spare_Parts.SparePart;
import teamproject.Customer_Account.Vehicle;

public class JobController {

	Collection<Payment> makesPayment;
	Collection<Invoice> creates;

	/**
	 * 
	 * @param jobID
	 */
	public Job getJob(int jobID) {
		// TODO - implement JobController.getJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param dataBookedIn
	 * @param status
	 * @param type
	 */
	public Job createJob(int jobID, Date dataBookedIn, String status, String type) {
		// TODO - implement JobController.createJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param task
	 */
	public EstimatedTask addEstimatedTask(int jobID, Task task) {
		// TODO - implement JobController.addEstimatedTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param taskID
	 */
	public void removeEstimatedTask(int jobID, int taskID) {
		// TODO - implement JobController.removeEstimatedTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public EstimatedTask[] getEstimatedTask(int jobID) {
		// TODO - implement JobController.getEstimatedTask
		throw new UnsupportedOperationException();
	}

	public Job[] getJobList() {
		// TODO - implement JobController.getJobList
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param paymentType
	 * @param invoiceNumber
	 */
	public Payment makePayment(String paymentType, int invoiceNumber) {
		// TODO - implement JobController.makePayment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param VAT
	 * @param invoiceNumber
	 * @param dateCreated
	 * @param JobID
	 */
	public Invoice createJobInvoice(double VAT, int invoiceNumber, Date dateCreated, int JobID) {
		// TODO - implement JobController.createJobInvoice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param VAT
	 * @param invoiceNumber
	 * @param dateCreated
	 * @param sparePartSold
	 */
	public Invoice createPartInvoice(double VAT, int invoiceNumber, Date dateCreated, SparePartSold[] sparePartSold) {
		// TODO - implement JobController.createPartInvoice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param invoiceNumber
	 */
	public void printInvoice(int invoiceNumber) {
		// TODO - implement JobController.printInvoice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param vehicle
	 */
	public Vehicle setJobVehicle(int jobID, Vehicle vehicle) {
		// TODO - implement JobController.setJobVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public Vehicle getJobVehicle(int jobID) {
		// TODO - implement JobController.getJobVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public void removeJobAllocatedVehicle(int jobID) {
		// TODO - implement JobController.removetJobAllocatedVehicle
		throw new UnsupportedOperationException();
	}

	public RepairBay getAvailableRepairBay() {
		// TODO - implement JobController.getAvailableRepairBay
		throw new UnsupportedOperationException();
	}

	public MoTInspectionBay getAvailableMoTInspectionBay() {
		// TODO - implement JobController.getAvailableMoTInspectionBay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param bayNumber
	 */
	public void allocateJobRepairBay(int jobID, int bayNumber) {
		// TODO - implement JobController.allocateJobRepairBay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param bayNumer
	 */
	public void allocatedJobMoTInspectionBay(int jobID, int bayNumer) {
		// TODO - implement JobController.allocatedJobMoTInspectionBay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public void removeJobMoTInspectionBay(int jobID) {
		// TODO - implement JobController.removeJobMoTInspectionBay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public void removeJobRepairBay(int jobID) {
		// TODO - implement JobController.removeJobRepairBay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public RepairBay getJobRepairbay(int jobID) {
		// TODO - implement JobController.getJobRepairbay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public MoTInspectionBay getJobMoTInspectionBay(int jobID) {
		// TODO - implement JobController.getJobMoTInspectionBay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param status
	 */
	public void setStatus(int jobID, String status) {
		// TODO - implement JobController.setStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public String getStatus(int jobID) {
		// TODO - implement JobController.getStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param taskID
	 */
	public void removeActualTask(int jobID, int taskID) {
		// TODO - implement JobController.removeActualTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobID
	 */
	public ActualTask[] getActualTask(int JobID) {
		// TODO - implement JobController.getActualTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param task
	 * @param actualHour
	 * @param actualCost
	 */
	public ActualTask addActualTask(int jobID, Task task, double actualHour, double actualCost) {
		// TODO - implement JobController.addActualTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobID
	 * @param sparePart
	 */
	public SparePartUsed addJobSparePartUsed(int JobID, SparePart sparePart) {
		// TODO - implement JobController.addJobSparePartUsed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobID
	 * @param sparePartID
	 */
	public void removeJobSparePartUsed(int JobID, String sparePartID) {
		// TODO - implement JobController.removeJobSparePartUsed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public SparePartUsed[] getJobSparePartUsed(int jobID) {
		// TODO - implement JobController.getJobSparePartUsed
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param mechanic
	 */
	public void allocateJobToMechanic(int jobID, Mechanic mechanic) {
		// TODO - implement JobController.allocateJobToMechanic
		throw new UnsupportedOperationException();
	}

	public static JobController JobController() {
		// TODO - implement JobController.JobController
		throw new UnsupportedOperationException();
	}

}