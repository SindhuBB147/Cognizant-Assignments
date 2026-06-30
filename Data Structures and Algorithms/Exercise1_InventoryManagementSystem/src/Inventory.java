import java.util.HashMap;

public class Inventory {

    private HashMap<Integer, Product> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Product Added Successfully.");
    }

    public void updateProduct(int id, String name, int quantity, double price) {

        Product product = inventory.get(id);

        if (product != null) {
            product.setProductName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Product Updated Successfully.");
        } else {
            System.out.println("Product Not Found.");
        }
    }

    public void deleteProduct(int id) {

        if (inventory.remove(id) != null) {
            System.out.println("Product Deleted Successfully.");
        } else {
            System.out.println("Product Not Found.");
        }
    }

    public void displayProducts() {

        if (inventory.isEmpty()) {
            System.out.println("Inventory is Empty.");
            return;
        }

        System.out.println("\nCurrent Inventory:");

        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}