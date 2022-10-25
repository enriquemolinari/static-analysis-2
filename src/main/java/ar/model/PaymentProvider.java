package ar.model;

public interface PaymentProvider {

    public void execute(double amount, String creditCardNumber, int securityCode);
}
