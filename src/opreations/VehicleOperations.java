package opreations;

import MySQLConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleOperations {

    // Park a new vehicle
    public void parkVehicle(String plateNumber, String ownerName, String vehicleType, int lotId) {
        String query = "INSERT INTO vehicles (plate_number, owner_name, vehicle_type, lot_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, plateNumber);
            pstmt.setString(2, ownerName);
            pstmt.setString(3, vehicleType);
            pstmt.setInt(4, lotId);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " vehicle(s) parked.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all parked vehicles
    public void getAllParkedVehicles() {
        String query = "SELECT * FROM vehicles WHERE exit_time IS NULL";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Vehicle ID: " + rs.getInt("vehicle_id") +
                        ", Plate Number: " + rs.getString("plate_number") +
                        ", Owner: " + rs.getString("owner_name") +
                        ", Type: " + rs.getString("vehicle_type") +
                        ", Entry Time: " + rs.getTimestamp("entry_time") +
                        ", Parking Lot ID: " + rs.getInt("lot_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mark a vehicle as exited
    public void exitVehicle(String plateNumber) {
        String query = "UPDATE vehicles SET exit_time = CURRENT_TIMESTAMP WHERE plate_number = ? AND exit_time IS NULL";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, plateNumber);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Vehicle " + plateNumber + " exited.");
            } else {
                System.out.println("Vehicle not found or already exited.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
