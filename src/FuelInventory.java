public class FuelInventory {

    public enum InventoryType {
        PETROL, DIESEL
    }

    public InventoryType inventoryType;
    private int id;
    private double availableQuantity;
    private static final double threshold = 20;
    FuelType.Grade grade;

    public FuelInventory(int id, InventoryType inventoryType, FuelType.Grade grade) {
        this.id = id;
        this.inventoryType = inventoryType;
        this.grade = grade;
    }

    public void getFuelInventoryDetails(String inventory) {
        System.out.println("Inventory Detail of " + inventory);
        System.out.println("Inventory ID " + id);
        System.out.println("Inventory Type " + inventoryType);
        System.out.println("Inventory Grade " + grade);
        System.out.println("Available Balance " + availableQuantity);
    }

    public double addFuelToInventory(double amount) {
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount must be a positive value.");
            }

            availableQuantity = availableQuantity + amount;
            return availableQuantity;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return availableQuantity; // Return current available quantity or handle accordingly based on  application logic
        }
    }

    public double updateDispensedQuantity(double dispensedAmount) {
        try {
            if (dispensedAmount < 0 || dispensedAmount > availableQuantity) {
                throw new IllegalArgumentException("Invalid dispensed amount. Check the amount and available quantity.");
            }

            availableQuantity = availableQuantity - dispensedAmount;
            return availableQuantity;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return availableQuantity; // Return current available quantity or handle accordingly based on  application logic
        }
    }



}
