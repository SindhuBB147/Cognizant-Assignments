public class WebApp implements Observer {

    public void update(String stockName, double price) {
        System.out.println("Web App: " + stockName + " price updated to Rs." + price);
    }
}