public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addProduct(new Product(101, "Laptop", 10, 65000));
        inventory.addProduct(new Product(102, "Mouse", 50, 500));
        inventory.addProduct(new Product(103, "Keyboard", 20, 1200));

        inventory.displayProducts();

        inventory.updateProduct(102, "Wireless Mouse", 45, 700);

        inventory.displayProducts();

        inventory.deleteProduct(101);

        inventory.displayProducts();
    }
}