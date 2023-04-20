package loan;

public class Loan {
    final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private double sum;
    private int term;
    private int repaymentSchedule;
    private double annualRate;

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setTerm(int term) {
        this.term = term;
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

    public int getRepaymentSchedule() {
        return repaymentSchedule;
    }

    public double getAnnualRate() {
        return annualRate;
    }
}
