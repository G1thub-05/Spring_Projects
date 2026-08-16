package in.digeshwar;

public class DebitCart implements Ipayment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " through Debit Cart.");
    }
}
