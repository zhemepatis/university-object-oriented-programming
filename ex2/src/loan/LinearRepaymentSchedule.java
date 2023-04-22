package loan;

public class LinearRepaymentSchedule extends RepaymentSchedule {
    public LinearRepaymentSchedule(Loan loan) {
        super(loan);
        createRepaymentSchedule();
    }

    @Override
    public void createRepaymentSchedule() {
        int term = loan.getTerm();
        double sum = loan.getSum();
        double rate = loan.getAnnualRate()/12;

        for(int i = 0; i < term; ++i) {
//            credit[i] = loan.getSum()/term;
//            balance[i] = sum;
//            interest[i] = sum*rate;
//            monthlyPay[i] = credit[i] + interest[i];
//
//            sum -= credit[i];
        }
    }

    @Override
    public void postponeRepayment(Loan loan) {

    }
}
