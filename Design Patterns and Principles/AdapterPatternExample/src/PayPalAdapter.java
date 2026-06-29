public class PayPalAdapter implements PaymentProcessor {

    private PayPalGateway payPalGateway;

    public PayPalAdapter() {
        payPalGateway = new PayPalGateway();
    }

    public void processPayment(double amount) {
        payPalGateway.sendPayment(amount);
    }
}