package teamproject.Jobs;

import teamproject.User_Accounts.Mechanic;
import Spare_Parts.SparePart;
import java.util.Date;
import teamproject.Customer_Account.Vehicle;
import teamproject.Jobs.SparePartUsed;
import teamproject.Jobs.Task;

public class Job {

    private int jobID;
    private String type;
    private Vehicle vehicle;

    public Job() {
    }


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
}
