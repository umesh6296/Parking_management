package parkingMain;

import opreations.ParkingLotOperations;
import opreations.VehicleOperations;

import java.util.Scanner;

public class CarParkingMain{
    public static void main(String[] args) {
        ParkingLotOperations lotOps = new ParkingLotOperations();
        VehicleOperations vehicleOps = new VehicleOperations();
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

            switch (choice) {
                case 1:
                    System.out.print("Enter lot name: ");
                    String lotName = scanner.nextLine();
                    System.out.print("Enter capacity: ");
                    int capacity = scanner.nextInt();
                    lotOps.addParkingLot(lotName, capacity);
                    break;

                case 2:
                    lotOps.getAllParkingLots();
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
                    vehicleOps.parkVehicle(plate, owner, type, lotId);
                    break;

                case 4:
                    vehicleOps.getAllParkedVehicles();
                    break;

                case 5:
                    System.out.print("Enter plate number to exit: ");
                    String exitPlate = scanner.nextLine();
                    vehicleOps.exitVehicle(exitPlate);
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
