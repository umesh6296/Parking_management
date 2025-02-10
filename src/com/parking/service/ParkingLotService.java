package com.parking.service;

import com.parking.dao.ParkingLotDAO;
import com.parking.dao.ParkingLotDAOImpl;
import com.parking.entity.ParkingLot;
import com.parking.exception.ParkingException;
import java.sql.SQLException;
import java.util.List;

public class ParkingLotService {
    private ParkingLotDAOImpl parkingLotDAOImpl = new ParkingLotDAOImpl();

    public void addParkingLot(ParkingLot parkingLot) throws ParkingException {
        try {
            parkingLotDAOImpl.addParkingLot(parkingLot);
        } catch (ParkingException e) {
            throw new ParkingException("Failed to add parking lot", e);
        }
    }

    public List<ParkingLot> getAllParkingLots() throws ParkingException {
        try {
            return parkingLotDAOImpl.getAllParkingLots();
        } catch (ParkingException e) {
            throw new ParkingException("Failed to retrieve parking lots", e);
        }
    }
}