package com.parking.main;

import com.parking.entity.ParkingLot;
import com.parking.entity.Vehicle;
import com.parking.exception.ParkingException;
import com.parking.service.ParkingLotService;
import com.parking.service.VehicleService;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class CarParkingMain {
    public static void main(String[] args) {
        ParkingLotService parkingLotService = new ParkingLotService();
        VehicleService vehicleService = new VehicleService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== CAR PARKING MANAGEMENT =====");
            System.out.println("1. Add Parking Lot");
            System.out.println("2. View Parking Lots");
            System.out.println("3. Park a Vehicle");
            System.out.println("4. View Parked Vehicles");
            System.out.println("5. Exit Vehicle");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter lot name: ");
                        String lotName = scanner.nextLine();
                        System.out.print("Enter capacity: ");
                        int capacity = scanner.nextInt();
                        ParkingLot parkingLot = new ParkingLot(0, lotName, capacity);
                        parkingLotService.addParkingLot(parkingLot);
                        System.out.println("Parking lot added successfully.");
                        break;

                    case 2:
                        List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
                        for (ParkingLot lot : parkingLots) {
                            System.out.println("Lot ID: " + lot.getLotId() +
                                    ", Name: " + lot.getLotName() +
                                    ", Capacity: " + lot.getCapacity());
                        }
                        break;

                    case 3:
                        System.out.print("Enter plate number: ");
                        String plate = scanner.nextLine();
                        System.out.print("Enter owner name: ");
                        String owner = scanner.nextLine();
                        System.out.print("Enter vehicle type: ");
                        String type = scanner.nextLine();
                        System.out.print("Enter parking lot ID: ");
                        int lotId = scanner.nextInt();
                        Vehicle vehicle = new Vehicle(0, plate, owner, type, new Timestamp(System.currentTimeMillis()), null, lotId);
                        vehicleService.parkVehicle(vehicle);
                        System.out.println("Vehicle parked successfully.");
                        break;

                    case 4:
                        List<Vehicle> vehicles = vehicleService.getAllParkedVehicles();
                        for (Vehicle v : vehicles) {
                            System.out.println("Vehicle ID: " + v.getVehicleId() +
                                    ", Plate Number: " + v.getPlateNumber() +
                                    ", Owner: " + v.getOwnerName() +
                                    ", Type: " + v.getVehicleType() +
                                    ", Entry Time: " + v.getEntryTime() +
                                    ", Parking Lot ID: " + v.getLotId());
                        }
                        break;

                    case 5:
                        System.out.print("Enter plate number to exit: ");
                        String exitPlate = scanner.nextLine();
                        vehicleService.exitVehicle(exitPlate);
                        System.out.println("Vehicle exited successfully.");
                        break;

                    case 6:
                        System.out.println("Exiting system...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (ParkingException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}