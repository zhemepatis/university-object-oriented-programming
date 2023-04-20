package loan;

public class AnnuityRepaymentSchedule extends RepaymentSchedule{
    double monthlyPay;
    double[] balance;
    double[] interest;
    double[] credit;

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
        double rate = loan.getAnnualRate()/term;
        double annuityCoefficient = rate*Math.pow((1+rate),term)/(Math.pow((1+rate),term) - 1);
        double sum = loan.getSum();
        monthlyPay = (double) Math.round(annuityCoefficient * sum * 100)/100;

        for(int i = 0; i < term; ++i) {
            balance[i] = (double) Math.round(sum*100)/100;
            interest[i] = (double) Math.round((sum*rate*100)/100);
            credit[i] = monthlyPay - interest[i];

            sum -= monthlyPay;
            System.out.println(credit[i]);
        }

        System.out.println(monthlyPay);
    }
}
