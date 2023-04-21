package loan;

public class AnnuityRepaymentSchedule extends RepaymentSchedule{
    private double monthlyPay;
    private double[] balance;
    private double[] interest;
    private double[] credit;

    public AnnuityRepaymentSchedule(Loan loan) {
        super(loan);

        balance = new double[loan.getTerm()];
        interest = new double[loan.getTerm()];
        credit = new double[loan.getTerm()];

        createRepaymentSchedule();
    }

    @Override
    public void createRepaymentSchedule() {
        int term = loan.getTerm();
        double rate = loan.getAnnualRate()/12;
        double annuityCoefficient = rate * Math.pow((1+rate), term) / (Math.pow((1+rate), term) - 1);
        double sum = loan.getSum();
        monthlyPay = annuityCoefficient * sum;

//        System.out.println(monthlyPay);

        for(int i = 0; i < term; ++i) {
            balance[i] = sum;
            interest[i] = sum*rate;
            credit[i] = monthlyPay - interest[i];

            sum -= credit[i];

//            System.out.println("balance: " + balance[i]);
//            System.out.println("interest: " + interest[i]);
//            System.out.println("credit: " + credit[i]);
//            System.out.println();
        }
    }

    @Override
    public void postponeRepayment() {

    }

    public double getMonthlyPay() {
        return (double) Math.round(monthlyPay*100)/100;
    }

    public double getBalance(int month) {
        return (double) Math.round(balance[month]*100)/100;
    }

    public double getInterest(int month) {
        return (double) Math.round(interest[month]*100)/100;
    }

    public double getCredit(int month) {
        return (double) Math.round(credit[month]*100)/100;
    }
}
