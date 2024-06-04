import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Reservation {
    private int reservationId;
    private String guestName;
    private Room room;

    public Reservation(int reservationId, String guestName, Room room) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
        room.setAvailable(false);
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }
}

public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int reservationIdCounter;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
        reservationIdCounter = 1;
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
        rooms.add(new Room(302, "Suite"));
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory());
            }
        }
    }

    public void makeReservation(String guestName, int roomNumber) {
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }
        if (selectedRoom != null) {
            reservations.add(new Reservation(reservationIdCounter++, guestName, selectedRoom));
            System.out.println("Reservation successful! Reservation ID: " + (reservationIdCounter - 1));
        } else {
            System.out.println("Sorry, the selected room is not available.");
        }
    }

    public void displayBookingDetails(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("Reservation ID: " + reservation.getReservationId());
                System.out.println("Guest Name: " + reservation.getGuestName());
                System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
                System.out.println("Room Category: " + reservation.getRoom().getCategory());
                return;
            }
        }
        System.out.println("Invalid reservation ID.");
    }

    public static void main(String[] args) {
        HotelReservationSystem reservationSystem = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHotel Reservation System Menu:");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    scanner.nextLine(); // Consume newline
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    reservationSystem.makeReservation(guestName, roomNumber);
                    break;
                case 3:
                    System.out.print("Enter reservation ID: ");
                    int reservationId = scanner.nextInt();
                    reservationSystem.displayBookingDetails(reservationId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
