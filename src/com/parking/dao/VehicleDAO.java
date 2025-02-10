package com.parking.dao;

import com.parking.entity.Vehicle;
import com.parking.exception.ParkingException;

import java.util.List;

public interface VehicleDAO {
    public void parkVehicle(Vehicle vehicle) throws ParkingException;
    public List<Vehicle> getAllParkedVehicles() throws ParkingException;
    public void exitVehicle(String plateNumber) throws ParkingException;

}
