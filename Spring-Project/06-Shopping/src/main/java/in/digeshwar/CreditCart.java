package in.digeshwar;

public class CreditCart implements Ipayment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " through Credit Cart.");
    }
}
