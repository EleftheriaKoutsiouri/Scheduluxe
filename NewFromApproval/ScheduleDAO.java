package NewFromApproval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Scheduluxe.Activity;
import Scheduluxe.DatabaseConnection;

public class ScheduleDAO {

    public String[] getTimeSlots() {
        String[] TIME_SLOTS = {
                "09:00-11:00", "11:00-13:00", "13:00-15:00",
                "15:00-17:00", "17:00-19:00", "19:00-21:00"
        };
        return TIME_SLOTS;
    }

    //TODO: THIS METHOD CAN BE INTEGRATED IN THE createSchedule
    // Method to search activities based on user preferences
    public List<Activity> searchActivities(int destinationId, List<Integer> typeIds, int budgetId) throws Exception {
        List<Activity> activityList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // build the query based on how many of the "types" the user has checked
        StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT a.ActivityID, a.ActivityName, a.Details, a.StartTime, a.EndTime " +
                        "FROM Activities a " +
                        "WHERE destinationID = ? AND budgetId = ? AND typeId IN (");

        for (int i = 0; i < typeIds.size(); i++) {
            sql.append("?");
            if (i < typeIds.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(") ORDER BY a.StartTime");

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, destinationId);
            stmt.setInt(2, budgetId);

            for (int i = 0; i < typeIds.size(); i++) {
                stmt.setInt(i + 3, typeIds.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Activity activity = new Activity(
                        rs.getInt("ActivityID"),
                        rs.getString("ActivityName"),
                        rs.getString("Details"),
                        rs.getString("StartTime"),
                        rs.getString("EndTime"));
                activityList.add(activity);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            throw new Exception("Error retrieving activities: " + e.getMessage());
        } finally {
            db.close();
            if (con != null) {
                con.close();
            }
        }
        return activityList;
    }

    //old name: assignActivitiesToTimeSlots
    public Schedule createSchedule(List<Activity> activities, int userId, int totalDays)
            throws Exception {

        int scheduleId = generateScheduleId();
        Map<Integer, Map<String, Activity>> schedule = new HashMap<>();
        String[] timeSlots = getTimeSlots();

        // Track already assigned activities to ensure uniqueness
        Set<Activity> assignedActivities = new HashSet<>();

        for (int day = 1; day <= totalDays; day++) {
            schedule.put(day, new LinkedHashMap<>());
            // use of LinkedHashMap instead of HashMap to keep the timeslots and their
            // assigned activities in order

            for (String timeSlot : timeSlots) {
                boolean activityScheduled = false;

                for (Activity activity : activities) {
                    if (assignedActivities.contains(activity)) {
                        continue; // Skip already assigned activities
                    }

                    String[] slotTimes = timeSlot.split("-");
                    String slotStartTime = slotTimes[0] + ":00";
                    String slotEndTime = slotTimes[1] + ":00";

                    // Check if activity fits in the time slot
                    if (activity.getStartTime().compareTo(slotStartTime) >= 0
                            && activity.getEndTime().compareTo(slotEndTime) <= 0) {
                        schedule.get(day).put(timeSlot, activity);
                        assignedActivities.add(activity);
                        activityScheduled = true;
                        break; // Move to the next time slot
                    }
                }

                // If no valid activity found for this time slot, fill with placeholder activity
                if (!activityScheduled) {
                    System.out.println("You should get free time");
                    // schedule.get(day).put(timeSlot, new Activity("Free Slot",
                    // timeSlot.split("-")[0], timeSlot.split("-")[1]));
                }
            }
        }

        Schedule overallSchedule = new Schedule(scheduleId, schedule, userId);

        return overallSchedule;
    }

    private int generateScheduleId() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT MAX(scheduleId) FROM Schedules";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            int newScheduleId = 1;
            if (rs.next()) {
                newScheduleId = rs.getInt(1) + 1;
            }
            rs.close();
            stmt.close();
            db.close();

            return newScheduleId;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }
    
}
