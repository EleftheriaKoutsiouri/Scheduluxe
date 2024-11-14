package ScheduluxeClasses;

public class Activity {
    private int activityId;
    private String activityName;
    private String details;
    private String startTime;
    private String endTime;

    // Constructor
    public Activity(int activityId, String activityName, String details, String startTime, String endTime) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //getters
    public String getActivityName(){
        return activityName;
    }
    public String getDetails(){
        return details;
    }



}
