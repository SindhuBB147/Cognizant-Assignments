public class StrategyTest {

    public static void main(String[] args) {

        PaymentContext payment1 = new PaymentContext(new CreditCardPayment());
        payment1.executePayment(5000);

        PaymentContext payment2 = new PaymentContext(new PayPalPayment());
        payment2.executePayment(2500);
    }
}