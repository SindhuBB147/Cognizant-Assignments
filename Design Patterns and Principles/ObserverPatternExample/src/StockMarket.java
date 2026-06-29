import java.util.ArrayList;

public class StockMarket implements Stock {

    private ArrayList<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setStock(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
}