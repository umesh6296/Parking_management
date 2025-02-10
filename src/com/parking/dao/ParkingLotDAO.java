package com.parking.dao;

import com.parking.entity.ParkingLot;
import com.parking.exception.ParkingException;

import java.util.List;

public interface ParkingLotDAO {
    public void addParkingLot(ParkingLot parkingLot) throws ParkingException;
    public List<ParkingLot> getAllParkingLots() throws ParkingException;

}
