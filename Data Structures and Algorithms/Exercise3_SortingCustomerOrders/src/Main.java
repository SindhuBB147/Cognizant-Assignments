public class Main {

    public static void main(String[] args) {

        Order[] orders = {
                new Order(101, "Sindhu", 4500),
                new Order(102, "Rahul", 2200),
                new Order(103, "Anita", 7800),
                new Order(104, "Kiran", 3200)
        };

        System.out.println("Bubble Sort:");

        SortOperations.bubbleSort(orders);

        for (Order order : orders) {
            System.out.println(order);
        }

        Order[] orders2 = {
                new Order(101, "Sindhu", 4500),
                new Order(102, "Rahul", 2200),
                new Order(103, "Anita", 7800),
                new Order(104, "Kiran", 3200)
        };

        System.out.println("\nQuick Sort:");

        SortOperations.quickSort(orders2, 0, orders2.length - 1);

        for (Order order : orders2) {
            System.out.println(order);
        }
    }
}