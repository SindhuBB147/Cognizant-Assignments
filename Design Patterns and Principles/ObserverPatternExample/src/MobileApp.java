public class MobileApp implements Observer {

    public void update(String stockName, double price) {
        System.out.println("Mobile App: " + stockName + " price updated to Rs." + price);
    }
}