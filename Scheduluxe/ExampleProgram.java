package Scheduluxe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExampleProgram {
    public static void main(String[] args) {
        //put dummy destination, types, budget, days
        int budgetId = 1; //1-3
        int destinationId= 2; //1-2

        List<Integer> typeIds = new ArrayList<>();
        typeIds.add(1);
        typeIds.add(2);     //1-4
        int typeId = 1;

        int totalDays = 3;   //1-3


        Schedule schedule = new Schedule();

        try {
            List<Activity> activities = schedule.searchActivities(destinationId, typeIds, budgetId);
            for (Activity activity:activities) {
                System.out.println("Activity: " + activity.getActivityName() +"     StartTime: "+ activity.getStartTime() +"    EndTime: "+ activity.getEndTime());
            }
            System.out.println("-----------------------------------------------------");
            Map<Integer, Map<String, Activity>> totalSchedule = schedule.assignActivitiesToTimeSlots(activities, totalDays);

            // Debug activity names
            for (Map.Entry<Integer, Map<String, Activity>> dayEntry : totalSchedule.entrySet()) {
                System.out.println("Day: " + dayEntry.getKey());
                for (Map.Entry<String, Activity> timeEntry : dayEntry.getValue().entrySet()) {
                    System.out.println("Time:" + timeEntry.getKey() + "     Activity: " + timeEntry.getValue().getActivityName());
                }
                System.out.println("---------------------------------------------------");
            }

        } catch (Exception e) {
            System.err.println("Error occured in searchActivities or in assignActivitiesToTimeSlots");
        }
                
    }
}