import java.util.HashMap;
import java.util.Map;

abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class BookingService {
    public void bookRoom(RoomInventory inventory, String roomType, Room roomDetails, int nights) {
        Map<String, Integer> availability = inventory.getRoomAvailability();
        int currentCount = availability.getOrDefault(roomType, 0);

        if (currentCount > 0) {
            double totalCost = roomDetails.getPricePerNight() * nights;
            inventory.updateAvailability(roomType, currentCount - 1);
            System.out.println("Booking Confirmation:");
            System.out.println("Room Type: " + roomType);
            System.out.println("Nights: " + nights);
            System.out.println("Total Price: " + totalCost);
            System.out.println("Booking successful!\n");
        } else {
            System.out.println("Booking failed: No " + roomType + " rooms available.\n");
        }
    }
}

class CancellationService {
    public void cancelBooking(RoomInventory inventory, String room