public class InvestmentComparison {
    public static void main(String[] args) {
        // Home Loan parameters
        double loanAmount = 1000000; //10 lakhs
        double interestRate = 10; // 10% annual interest rate
        int tenure = 15; // 15 years
        double depreciationRate = 5; // 5% depr rate
        double appreciationRate = 3; // 3% appr rate

        // Create HomeLoan object
        HomeLoan homeLoan = new HomeLoan(loanAmount, interestRate, tenure, depreciationRate, appreciationRate);

        // Call the HomeLoan method to calculate and print amortization schedule
        homeLoan.calculateAmortization();

        // SIP parameters
        double monthlyInvestment = 10000; // Rs. 10,000 monthly SIP
        double sipRate = 12; // 12% annual SIP return rate
        int sipTenure = 15; // 15 years

        // Create SIP object
        SIP sip = new SIP(monthlyInvestment, sipRate, sipTenure);

        // Call the SIP method to calculate maturity amount
        double sipMaturity = sip.calculateMaturityAmount();

        // Display SIP maturity value
        System.out.printf("\nTotal SIP Maturity Value after %d years: Rs. %.2f\n", sipTenure, sipMaturity);

        // Get the final depreciated and appreciated values of the home
        double finalDepreciatedValue = homeLoan.getFinalDepreciatedValue();
        double finalAppreciatedValue = homeLoan.getFinalAppreciatedValue();

        // Print comparison results
        System.out.println("\nComparison of SIP Maturity Value with Home Loan Values:");

        // Compare SIP maturity with depreciated and appreciated home values
        if (sipMaturity > finalAppreciatedValue) {
            System.out.println("SIP value is greater than the appreciated home value.");
        } else {
            System.out.println("SIP value is less than or equal to the appreciated home value.");
        }

        if (sipMaturity > finalDepreciatedValue) {
            System.out.println("SIP value is greater than the depreciated home value.");
        } else {
            System.out.println("SIP value is less than or equal to the depreciated home value.");
        }
    }
}
