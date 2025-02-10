package com.parking.dao;

import com.parking.entity.ParkingLot;

import com.parking.exception.ParkingException;
import com.parking.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotDAOImpl implements ParkingLotDAO{

    public void addParkingLot(ParkingLot parkingLot) throws ParkingException {
        String query = "INSERT INTO parking_lots (lot_name, capacity) VALUES (?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, parkingLot.getLotName());
            pstmt.setInt(2, parkingLot.getCapacity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ParkingLot> getAllParkingLots() throws ParkingException {
        List<ParkingLot> parkingLots = new ArrayList<>();
        String query = "SELECT * FROM parking_lots";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ParkingLot lot = new ParkingLot();
                lot.setLotId(rs.getInt("lot_id"));
                lot.setLotName(rs.getString("lot_name"));
                lot.setCapacity(rs.getInt("capacity"));
                parkingLots.add(lot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parkingLots;
    }
}