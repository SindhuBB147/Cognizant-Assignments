public class StripeAdapter implements PaymentProcessor {

    private StripeGateway stripeGateway;

    public StripeAdapter() {
        stripeGateway = new StripeGateway();
    }

    public void processPayment(double amount) {
        stripeGateway.makePayment(amount);
    }
}