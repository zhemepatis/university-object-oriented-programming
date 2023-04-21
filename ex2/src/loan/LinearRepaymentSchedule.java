package loan;

public class LinearRepaymentSchedule extends RepaymentSchedule {
    private double[] monthlyPay;
    private double credit;
    private double[] balance;
    private double[] interest;

    public LinearRepaymentSchedule(Loan loan) {
        super(loan);

        balance = new double[loan.getTerm()];
        interest = new double[loan.getTerm()];
        monthlyPay = new double[loan.getTerm()];

        createRepaymentSchedule();
    }

    public double getMonthlyPay(int month) {
        return (double) Math.round(monthlyPay[month]*100)/100;
    }

    public double getBalance(int month) {
        return (double) Math.round(balance[month]*100)/100;
    }

    public double getInterest(int month) {
        return (double) Math.round(interest[month]*100)/100;
    }

    public double getCredit() {
        return (double) Math.round(credit*100)/100;
    }

    @Override
    public void createRepaymentSchedule() {
        int term = loan.getTerm();
        double sum = loan.getSum();
        credit = sum/term;
        double rate = loan.getAnnualRate()/12;

        for(int i = 0; i < term; ++i) {
            balance[i] = sum;
            interest[i] = sum*rate;
            monthlyPay[i] = credit + interest[i];

            sum -= credit;
        }
    }

    @Override
    public void postponeRepayment() {

    }
}
