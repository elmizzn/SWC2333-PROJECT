public class Inventory {
    // Attributes of the inventory item
    private String inventoryId;
    private String name;
    private String description;
    private double unitPrice;
    private int quantity;
    private boolean available;
    private String status;

    // Constructor to initialize the inventory object
    public Inventory(String inventoryId, String name, String description, double unitPrice, int quantity, boolean available, String status) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.available = available;
        this.status = status;
    }

    // Getter and setter methods for each attribute
    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to display inventory details
    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId='" + inventoryId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", available=" + available +
                ", status='" + status + '\'' +
                '}';
    }
}
