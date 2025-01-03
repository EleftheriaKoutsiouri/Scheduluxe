package Scheduluxe;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class Schedule {

    // Define the TIME_SLOTS array as a static final constant
    public static final String[] TIMESLOTS = {
        "09:00-11:00", "11:00-13:00", "13:00-15:00",
        "15:00-17:00", "17:00-19:00", "19:00-21:00"
};

    private int scheduleId;
    private Map<Integer, Map<String, Activity>> overallSchedule;
    private int userId;
    private String comment;
    private int rating;
    private int totalDays;
    private String savedDate;
    private Destination destination;
    
    //for the creation of the schedule the first time (CreationScheduleServlet.java via ScheduleDAO.java)
    public Schedule(int scheduleId, Map<Integer, Map<String, Activity>> overallSchedule, int userId, int totalDays) {
        this.scheduleId = scheduleId;
        this.overallSchedule = overallSchedule;
        this.userId = userId;
        this.totalDays = totalDays;
    }

    //for the retrieval of data when the user want to see a specific past schedule (from Editprofile.jsp to ShowOverallSchedule.jsp via ScheduleDAO.java)
    public Schedule(int scheduleId, Map<Integer, Map<String, Activity>> overallSchedule, int userId, int totalDays, String comment, int rating) {
        this.scheduleId = scheduleId;
        this.overallSchedule = overallSchedule;
        this.userId = userId;
        this.totalDays = totalDays;
        this.comment = comment;
        this.rating = rating;
    }

    //for the retrieval of data when the user want to see his/her past 2 last schedules (EditProfile.jsp via ScheduleDAO.java)
    public Schedule (int scheduleId, int totalDays, String savedDate, Destination dest) {
        this.scheduleId = scheduleId;
        this.totalDays = totalDays;
        this.savedDate = savedDate;
        this.destination = dest;
    }

    /* getters */
    public int getScheduleId() {
        return scheduleId;
    }
    public Map<Integer, Map<String, Activity>> getOverallSchedule() {
        return overallSchedule;
    }
    public int getUserId() {
        return userId;
    }
    public String getComment() {
        if (comment == null || comment.isEmpty()) {
            return "no comment";
        }
        return comment;
    }
    public int getRating() {
        return rating;
    }
    public int getTotalDays() {
        return totalDays;
    }
    public String getSavedDate() {
        return savedDate;
    }
    public Destination getDestination() {
        return destination;
    }

    /* setters when needed*/
    public void setComment(String newComment) {
        this.comment = newComment;
    }
    public void setRating(int newRating) {
        this.rating = newRating;
    }


    /* other methods*/

    public void saveFeedback() throws Exception {
        
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "UPDATE schedulesbytraveler SET comment = ?, rating = ?, savedDate = NOW() WHERE UserID = ? AND scheduleId = ?";
        
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, this.comment);
            stmt.setInt(2, this.rating);
            stmt.setInt(3, this.userId);
            stmt.setInt(4, this.scheduleId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                stmt.close();
                db.close();
            }

            stmt.close();
            db.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
            }
        }
    }
    
    //save the components of the object Schedule in 2 tables
    public void saveSchedule() throws Exception {
        saveInSchedules();
        saveScheduleForUser();
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

    private void saveScheduleForUser() throws Exception {
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
