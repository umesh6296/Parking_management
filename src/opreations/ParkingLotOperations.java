package opreations;

import MySQLConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingLotOperations {

    // Add a new parking lot
    public void addParkingLot(String lotName, int capacity) {
        String query = "INSERT INTO parking_lots (lot_name, capacity) VALUES (?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, lotName);
            pstmt.setInt(2, capacity);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " parking lot(s) added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all parking lots
    public void getAllParkingLots() {
        String query = "SELECT * FROM parking_lots";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Lot ID: " + rs.getInt("lot_id") +
                        ", Name: " + rs.getString("lot_name") +
                        ", Capacity: " + rs.getInt("capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
