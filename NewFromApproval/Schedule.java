package NewFromApproval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import Scheduluxe.Activity;
import Scheduluxe.DatabaseConnection;

public class Schedule {

    private int scheduleId;
    private Map<Integer, Map<String, Activity>> overallSchedule;
    private int userId;
    private String comment;
    private int rating;
    
    public Schedule(int scheduleId, Map<Integer, Map<String, Activity>> overallSchedule) {
        this.scheduleId = scheduleId;
        this.overallSchedule = overallSchedule;
    }


    /* getters */
    public int getScheduleId() {
        return scheduleId;
    }
    public Map<Integer, Map<String, Activity>> getOverallSchedule() {
        return overallSchedule;
    }
    public String getComment() {
        return comment;
    }
    public int getRating() {
        return rating;
    }

    /* setters when needed*/
    public void setComment(String newComment) {

    }
    public void setRating(int newRating) {
        
    }


    /* other methods*/
    
    //save the components of the object Schedule in 2 tables
    public void saveSchedule(int totalDays) throws Exception {
        saveInSchedules();
        saveScheduleForUser(totalDays);
    }

    private void saveInSchedules() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        String sql = "INSERT INTO Schedules (scheduleId, activityId, day, timeSlot) VALUES (?, ?, ?, ?)";

        try {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            for (Map.Entry<Integer, Map<String, Activity>> dayEntry : overallSchedule.entrySet()) {
                int day = dayEntry.getKey();

                Map<String, Activity> activitiesBySlot = new LinkedHashMap<>(dayEntry.getValue());

                activitiesBySlot.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey()) // Ταξινόμηση βάσει του timeSlot
                        .forEachOrdered(slotEntry -> {
                            try {
                                String timeSlot = slotEntry.getKey();
                                Activity activity = slotEntry.getValue();

                                stmt.setInt(1, scheduleId); // Το ίδιο scheduleId για όλο το πρόγραμμα
                                stmt.setInt(2, activity.getActivityId()); // Το ID της δραστηριότητας
                                stmt.setInt(3, day); // Ημέρα
                                stmt.setString(4, timeSlot); // Χρονικό διάστημα

                                stmt.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
            }

            stmt.close();
            db.close();
        } catch (Exception e) {
            throw new Exception("Error saving schedule to database: " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }

    private void saveScheduleForUser(int totalDays) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "INSERT INTO schedulesbytraveler (UserID, scheduleId, savedDate, days) VALUES (?, ?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, userId);
            stmt.setInt(2, scheduleId);
            stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            stmt.setInt(4, totalDays);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                stmt.close();
                db.close();
                throw new Exception("Error inserting schedule for user!");
            }

            stmt.close();
            db.close();

        } catch (Exception e) {
            throw new Exception("Error inserting schedule for user: " + e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {

            }
        }
    }
    
}
