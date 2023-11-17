package model.payment;

import java.util.Scanner;

public class CreditCardPayment implements PaymentStrategy {
    private String creditCardNumber;
    private String expirationDate;
    private String cvv;

    @Override
    public void pay(double amount) {
        System.out.println("Será redireccionado a la pasarela de pago correspondiente.");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Realizando pago con tarjeta de crédito. Monto: $" + amount);
    }
}
