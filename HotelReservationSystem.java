import java.util.ArrayList;
import java.util.Scanner;

// Room class
class Room {
    int roomNumber;
    String category;
    double price;
    boolean isBooked;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }
}

// Reservation class
class Reservation {
    String customerName;
    Room room;

    Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }
}

public class HotelReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {

        // Initialize rooms
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));

        int choice;
        do {
            System.out.println("\n🏨 HOTEL RESERVATION SYSTEM");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    // View available rooms
    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room " + r.roomNumber +
                        " | " + r.category +
                        " | ₹" + r.price);
            }
        }
    }

    // Book a room
    static void bookRoom() {
        viewRooms();

        System.out.print("\nEnter room number to book: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {

                System.out.print("Enter customer name: ");
                String name = sc.nextLine();

                System.out.println("Processing payment of ₹" + r.price + "...");
                System.out.println("Payment successful ✅");

                r.isBooked = true;
                reservations.add(new Reservation(name, r));

                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room not available!");
    }

    // Cancel reservation
    static void cancelReservation() {
        System.out.print("\nEnter room number to cancel booking: ");
        int roomNo = sc.nextInt();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation res = reservations.get(i);
            if (res.room.roomNumber == roomNo) {
                res.room.isBooked = false;
                reservations.remove(i);
                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }
        System.out.println("No reservation found!");
    }

    // View all bookings
    static void viewBookings() {
        System.out.println("\n📋 Booking Details:");
        if (reservations.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Reservation r : reservations) {
                System.out.println("Customer: " + r.customerName +
                        " | Room: " + r.room.roomNumber +
                        " | Category: " + r.room.category);
            }
        }
    }
}
