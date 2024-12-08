public class HomeLoan {
    private double loanAmount;
    private double interestRate;
    private int tenure;
    private double appreciationRate;
    private double salvageValue; // Salvage value of the home
    private double finalAppri;
    private double finalDepri;
    public HomeLoan(double loanAmount, double interestRate, int tenure, double appreciationRate,double salvage){
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.appreciationRate = appreciationRate;
        this.salvageValue = salvage;
    }

    private void calculateDepreciationAndAppreciation(int year) {
        double appreciatedValue = loanAmount * Math.pow(1 + appreciationRate / 100, year); //appreciatin of that year
        //depreciation calc
        double assetCost = appreciatedValue;
        double depreciationRate=(assetCost - salvageValue)/tenure; // Depreciation raet per year
        double depreciatedValueOfYear=  assetCost-(depreciationRate*year);
        System.out.printf("Year %d - Depreciated Value: Rs. %.2f, Appreciated Value: Rs. %.2f\n", year, depreciatedValueOfYear, appreciatedValue);

        if(year==15){
            finalAppri = appreciatedValue;
            finalDepri = depreciatedValueOfYear;
        }
    }

    public void calculateAmortization(){
        double principal = loanAmount; // Starting balance (initial loan amount)
        double monthlyRoi = interestRate / 100 / 12; // Monthly interest rate
        int months = tenure * 12; // Total number of months in the loan tenure for us = 180mont (15 years * 12)

        double emi = loanAmount * (monthlyRoi*Math.pow(1+monthlyRoi,months))/(Math.pow(1+monthlyRoi,months)-1);

        System.out.println("\nHome Loan Amortization Schedule:");
        System.out.printf("%-5s %-15s %-15s %-15s\n", "Year", "Interest", "Principal", "Ending Balance");

        // Calculate and print amortization for each year
        for (int year = 1; year <= tenure; year++) {
            double yearlyInt = 0;
            double yearlyPri = 0;
            for (int month = 1; month <= 12; month++) {
                double interestPayment = principal * monthlyRoi;
                double principalPayment = emi - interestPayment;
                principal -= principalPayment;
                yearlyInt += interestPayment;
                yearlyPri += principalPayment;
            }
            System.out.printf("%-5d %-15.2f %-15.2f %-15.2f\n", year, yearlyInt, yearlyPri, principal);
        }
        for (int year = 1; year <= tenure; year++) {
            calculateDepreciationAndAppreciation(year);
        }
    }
    public double getFinalDepreciatedValue() {
        return finalDepri;
    }
    public double getFinalAppreciatedValue() {
        return finalAppri;
    }
}
