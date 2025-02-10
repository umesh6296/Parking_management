package com.parking.service;

import com.parking.dao.VehicleDAOImpl;
import com.parking.entity.Vehicle;
import com.parking.exception.ParkingException;
import java.sql.SQLException;
import java.util.List;

public class VehicleService {
    private VehicleDAOImpl vehicleDAOImpl = new VehicleDAOImpl();

    public void parkVehicle(Vehicle vehicle) throws ParkingException {
        try {
            vehicleDAOImpl.parkVehicle(vehicle);
        } catch (ParkingException e) {
            throw new ParkingException("Failed to park vehicle", e);
        }
    }

    public List<Vehicle> getAllParkedVehicles() throws ParkingException {
        try {
            return vehicleDAOImpl.getAllParkedVehicles();
        } catch (ParkingException e) {
            throw new ParkingException("Failed to retrieve parked vehicles", e);
        }
    }

    public void exitVehicle(String plateNumber) throws ParkingException {
        try {
            vehicleDAOImpl.exitVehicle(plateNumber);
        } catch (ParkingException e) {
            throw new ParkingException("Failed to exit vehicle", e);
        }
    }
}