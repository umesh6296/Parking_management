package com.parking.entity;

public class ParkingLot {
    private int lotId;
    private String lotName;
    private int capacity;

    // Constructors, Getters, and Setters
    public ParkingLot() {}

    public ParkingLot(int lotId, String lotName, int capacity) {
        this.lotId = lotId;
        this.lotName = lotName;
        this.capacity = capacity;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}