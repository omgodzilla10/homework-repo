package firm;

public class Commission extends Hourly {
    private double totalSales;
    private double commRate;

    public Commission(String name, String address, String phNumber, String ssn,
                      double payRate, double commRate) {
        super(name, address, phNumber, ssn, payRate);
        this.commRate = commRate;
    }

    public void addSales(double totalSales) {
        this.totalSales += totalSales;
    }

    public double pay() {
        double payment = super.pay();
        payment += (commRate * totalSales);
        totalSales = 0;

        return payment;
    }

    public String toString() {
        String info = super.toString();
        info += ("\nTotal Sales: " + totalSales);

        return info;
    }
}
