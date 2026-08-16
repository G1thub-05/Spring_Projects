package in.digeshwar;
public class ATM {
    private Iprinter Sprinter;
    private Iprinter Hprinter;

    public ATM(Iprinter s) {
        this.Sprinter = s;
    }

    public void Sprint(int number) {
        System.out.println("Withdraw Amount " + number + " Rs. From Sony ATM");
        Sprinter.print();
    }

    public void setMyhp(Iprinter h) {
        this.Hprinter = h;
    }

    public void Hprint(int number) {
        System.out.println("Withdraw Amount " + number + " Rs. From HP ATM");
        Hprinter.print();
    }
}
