package com.parking.dao;

import com.parking.entity.Vehicle;

import com.parking.exception.ParkingException;
import com.parking.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO{

    public void parkVehicle(Vehicle vehicle) throws ParkingException {
        String query = "INSERT INTO vehicles (plate_number, owner_name, vehicle_type, lot_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, vehicle.getPlateNumber());
            pstmt.setString(2, vehicle.getOwnerName());
            pstmt.setString(3, vehicle.getVehicleType());
            pstmt.setInt(4, vehicle.getLotId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> getAllParkedVehicles() throws ParkingException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE exit_time IS NULL";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("vehicle_id"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setOwnerName(rs.getString("owner_name"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setEntryTime(rs.getTimestamp("entry_time"));
                vehicle.setLotId(rs.getInt("lot_id"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public void exitVehicle(String plateNumber) throws ParkingException {
        String query = "UPDATE vehicles SET exit_time = CURRENT_TIMESTAMP WHERE plate_number = ? AND exit_time IS NULL";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, plateNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}