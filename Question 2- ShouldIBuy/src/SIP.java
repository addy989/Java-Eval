public class SIP {
    private double monthlyInvestment;
    private double annualRate;
    private int tenureYears;
    public SIP(double monthlyInvestment, double annualRate, int tenureYears) {
        this.monthlyInvestment = monthlyInvestment;
        this.annualRate = annualRate;
        this.tenureYears = tenureYears;
    }

    public double calculateMaturityAmount() {
        double monthlyROR = annualRate / 100 / 12; // Monthly rate of return
        int months = tenureYears * 12;
        //M = P × ({[1 + i]^n – 1} / i) × (1 + i)
        double maturityAmount = monthlyInvestment * ((Math.pow(1 + monthlyROR, months) - 1) / monthlyROR) *(1 + monthlyROR);
        return maturityAmount;
    }
}
