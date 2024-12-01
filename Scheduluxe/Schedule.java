package Scheduluxe;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    // Constructor
    public Schedule() {
    }

    // Method to search activities based on user preferences
    public List<Activity> searchActivities(int destinationId, int typeId, int budgetId) throws Exception {
        List<Activity> activityList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // SQL query to find matching activities based on preferences
        String sql = "SELECT DISTINCT a.ActivityID, a.ActivityName, a.Details, a.StartTime, a.EndTime " +
                "FROM Activities a " +
                "WHERE destinationID = ? AND typeId = ? AND budgetId = ? " +
                "ORDER BY a.StartTime";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, destinationId);
            stmt.setInt(2, typeId);
            stmt.setInt(3, budgetId);
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
            if (con != null) {
                con.close();
            }
        }
        return activityList;
    }

    public Map<Integer, Map<String, Activity>> assignActivitiesToTimeSlots(List<Activity> activities, int totalDays)
            throws Exception {
        // Χάρτης που περιέχει τις δραστηριότητες για κάθε ημέρα και time slot
        Map<Integer, Map<String, Activity>> schedule = new HashMap<>();

        // Fixed time slots
        String[] timeSlots = { "09:00-11:00", "11:00-13:00", "13:00-15:00", "15:00-17:00", "17:00-19:00",
                "19:00-21:00" };

        // Προετοιμασία για κατανομή
        int day = 1;
        int currentTimeSlotIndex = 0;

        // Επανάληψη για όλες τις δραστηριότητες
        for (Activity activity : activities) {
            // Δημιουργία χάρτη για την ημέρα αν δεν υπάρχει
            schedule.computeIfAbsent(day, k -> new HashMap<>());

            // Εύρεση κατάλληλου time slot
            while (currentTimeSlotIndex < timeSlots.length) {
                String slot = timeSlots[currentTimeSlotIndex];
                String[] times = slot.split("-");
                String slotStartTime = times[0] + ":00";
                String slotEndTime = times[1] + ":00";

                // Έλεγχος αν η δραστηριότητα ταιριάζει στο time slot
                if (activity.getStartTime().compareTo(slotStartTime) >= 0
                        && activity.getStartTime().compareTo(slotEndTime) < 0) {
                    // Αντιστοίχιση δραστηριότητας στο time slot
                    schedule.get(day).put(slot, activity);
                    break;
                }
                currentTimeSlotIndex++;
            }

            // Μετάβαση στην επόμενη ημέρα αν όλα τα time slots έχουν γεμίσει
            if (schedule.get(day).size() == timeSlots.length && day < totalDays) {
                day++;
                currentTimeSlotIndex = 0; // Επαναφορά στο πρώτο slot
            }
        }

        return schedule;
    }

    public void saveSchedule(Map<Integer, Map<String, Activity>> schedule) throws Exception {
        // Δημιουργία σύνδεσης με τη βάση δεδομένων
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // SQL εντολή για εισαγωγή δεδομένων στον πίνακα Schedules
        String sql = "INSERT INTO Schedules (activityId, day, timeSlot) VALUES (?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            // Επανάληψη μέσω του χάρτη για κάθε ημέρα
            for (Map.Entry<Integer, Map<String, Activity>> dayEntry : schedule.entrySet()) {
                int day = dayEntry.getKey();
                Map<String, Activity> activitiesBySlot = dayEntry.getValue();

                // Επανάληψη μέσω των δραστηριοτήτων ανά time slot
                for (Map.Entry<String, Activity> slotEntry : activitiesBySlot.entrySet()) {
                    String timeSlot = slotEntry.getKey();
                    Activity activity = slotEntry.getValue();

                    // Εισαγωγή δεδομένων στη βάση
                    stmt.setInt(1, activity.getActivityId());
                    stmt.setInt(2, day);
                    stmt.setString(3, timeSlot);

                    stmt.executeUpdate();
                }
            }

            stmt.close();
        } catch (Exception e) {
            throw new Exception("Error saving schedule to database: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void saveScheduleForUser(int userId, int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;

        // SQL query για την εισαγωγή δεδομένων στον πίνακα schedulebytraveler
        String sql = "INSERT INTO schedulebytraveler (UserID, scheduleId, savedDate) VALUES (?, ?, ?)";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            // Ορισμός των παραμέτρων του PreparedStatement
            stmt.setInt(1, userId); // Χρησιμοποιούμε το userId από το session ή τη βάση
            stmt.setInt(2, scheduleId); // Χρησιμοποιούμε το scheduleId από το πρόγραμμα
            stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis())); // Χρησιμοποιούμε την τρέχουσα
                                                                                      // ημερομηνία και ώρα

            // Εκτέλεση της εισαγωγής
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Schedule successfully linked to the user.");
            } else {
                System.out.println("Error occurred while associating schedule with user.");
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error inserting schedule for user: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int getScheduleId() throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        int scheduleId = -1;

        String sql = "SELECT LAST_INSERT_ID() AS scheduleId FROM Schedules";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                scheduleId = rs.getInt("scheduleId");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error retrieving scheduleId: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return scheduleId; // Επιστρέφουμε το scheduleId
    }

    // Αποθήκευση σχολίων και αξιολογήσεων
    public boolean saveFeedback(int scheduleId, String comment, int rating) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "UPDATE Schedules SET Comment = ?, Rate = ? WHERE ScheduleID = ?";

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            // Εισαγωγή παραμέτρων
            stmt.setString(1, comment);
            stmt.setInt(2, rating);
            stmt.setInt(3, scheduleId);

            // Εκτέλεση του update
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Ανάκτηση του προγράμματος μαζί με το σχόλιο και την αξιολόγηση
    public Map<String, Object> viewSchedule(int scheduleId) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = null;
        String sql = "SELECT ActivityID, Day, time_slot, Comment, Rate FROM Schedules WHERE ScheduleID = ?";

        Map<String, Object> scheduleData = new HashMap<>();

        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, scheduleId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                scheduleData.put("ActivityID", rs.getInt("ActivityID"));
                scheduleData.put("Day", rs.getInt("Day"));
                scheduleData.put("TimeSlot", rs.getString("time_slot"));
                scheduleData.put("Comment", rs.getString("Comment"));
                scheduleData.put("Rate", rs.getInt("Rate"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error retrieving schedule details: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return scheduleData; // Επιστροφή του Map με τα δεδομένα
    }
}
