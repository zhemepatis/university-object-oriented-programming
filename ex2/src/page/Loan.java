package page;

public class Loan {
    private double sum;
    private int term;
    private int termUnits;
    private int repaymentSchedule;
    private double annualRate;

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setTerm(int term, int termUnits) {
        this.term = term;
        this.termUnits = termUnits;
    }

    public void setRepaymentSchedule(int repaymentSchedule) {
        this.repaymentSchedule = repaymentSchedule;
    }

    public void setAnnualRate(double annualRate) {
        this.annualRate = annualRate;
    }

    public double getSum() {
        return sum;
    }

    public int getTerm() {
        return term;
    }

    public int getTermUnits() {
        return termUnits;
    }

    public int getRepaymentSchedule() {
        return repaymentSchedule;
    }

    public double getAnnualRate() {
        return annualRate;
    }
}
