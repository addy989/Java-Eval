public class InvestmentComparison {
    public static void main(String[] args) {
        double homevalue = 1000000;
        double interestRate = 10;
        int tenure = 15;

        double appreciationRate = 3;
        double salvage = 500000;

        HomeLoan home = new HomeLoan(homevalue, interestRate, tenure,appreciationRate,salvage);
        home.calculateAmortization();

        double monthlyinvestment = 10000;
        double sipRate = 12;

        SIP sip = new SIP(monthlyinvestment, sipRate, tenure);

        double sipMaturity = sip.calculateMaturityAmount();

        // Display SIP maturity value
        System.out.println("Total SIP Maturity Value after " + tenure + " years: Rs. " + sipMaturity);
        double finalDepreciatedValue = home.getFinalDepreciatedValue();
        double finalAppreciatedValue = home.getFinalAppreciatedValue();

        System.out.println("Final Home Values after 15 years:");
        System.out.printf("Final Depreciated Value of Home: Rs. %.2f\n", finalDepreciatedValue);
        System.out.printf("Final Appreciated Value of Home: Rs. %.2f\n", finalAppreciatedValue);

        System.out.println("\nFinal SIP Value after 15 years:");
        System.out.printf("SIP Maturity Value: Rs. %.2f\n", sipMaturity);
    }
}
