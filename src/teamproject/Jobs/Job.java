package teamproject.Jobs;

import teamproject.Customer_Account.Vehicle;
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
