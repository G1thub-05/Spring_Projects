package in.digeshwar;

public class Upi implements Ipayment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " through Upi.");
    }
}
