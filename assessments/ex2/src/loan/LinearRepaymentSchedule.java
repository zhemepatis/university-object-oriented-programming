package loan;

public class LinearRepaymentSchedule extends RepaymentSchedule {
    public LinearRepaymentSchedule(Loan loan) {
        super(loan);
        createRepaymentSchedule();
    }

    @Override
    public void createRepaymentSchedule() {
        int term = loan.getTerm();
        double currBalance = loan.getSum();
        double rate = loan.getAnnualRate()/12;
        double currCredit = loan.getSum()/term;

        for(int i = 0; i < term; ++i) {
            double currInterest = currBalance*rate;
            double currMonthPay = currInterest + currCredit;

            credit.add(currCredit);
            balance.add(currBalance);
            interest.add(currInterest);
            monthlyPay.add(currMonthPay);

            currBalance -= currCredit;
        }
    }

    @Override
    public void postponeRepayment(Loan loan) {
        int term = loan.getTerm();
        int postTerm = loan.getPostTerm();
        int startDate = loan.getPostDate();
        int endDate = startDate + postTerm;

        double currBalance = balance.get(startDate);
        duration = term + postTerm;

        for(int i = startDate; i  < endDate; ++i) {
            double currRate = loan.getPostRate()/12;
            double currInterest = currRate*currBalance;
            double currMonthPay = currInterest;
            double currCredit = 0.0;

            balance.set(i, currBalance);
            monthlyPay.set(i, currMonthPay);
            interest.set(i, currInterest);
            credit.set(i, currCredit);

            balance.add(0.0);
            monthlyPay.add(0.0);
            interest.add(0.0);
            credit.add(0.0);
        }

        double rate = loan.getAnnualRate()/12;
        double currCredit = loan.getSum()/term;

        for(int i = endDate; i < duration; ++i) {
            double currInterest = currBalance*rate;
            double currMonthPay = currInterest+currCredit;

            balance.set(i, currBalance);
            monthlyPay.set(i, currMonthPay);
            interest.set(i, currInterest);
            credit.set(i, currCredit);

            currBalance -= currCredit;
        }
    }
}
