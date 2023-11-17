package model.payment;

import java.util.Scanner;

public class NequiPayment implements PaymentStrategy {
    private String phoneNumber;
    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su número de teléfono: ");
        this.phoneNumber = scanner.nextLine();
        System.out.println("Realizando pago con Nequi. Monto: $" + amount);
        scanner.close();
    }
}
