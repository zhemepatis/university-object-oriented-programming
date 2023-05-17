package loan;

public class AnnuityRepaymentSchedule extends RepaymentSchedule{
    double annuityCoefficient;

    public AnnuityRepaymentSchedule(Loan loan) {
        super(loan);

        double rate = loan.getAnnualRate()/12;
        int term = loan.getTerm();
        annuityCoefficient = rate * Math.pow((1+rate), term) / (Math.pow((1+rate), term) - 1);

        createRepaymentSchedule();
    }

    @Override
    public void createRepaymentSchedule() {
        int term = loan.getTerm();
        double rate = loan.getAnnualRate()/12;
        double currBalance = loan.getSum();
        double currMonthPay = annuityCoefficient * loan.getSum();

        for(int i = 0; i < term; ++i) {
            double currInterest = currBalance*rate;
            double currCredit = currMonthPay - currInterest;

            balance.add(currBalance);
            monthlyPay.add(currMonthPay);
            interest.add(currInterest);
            credit.add(currCredit);

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
        double currMonthPay = annuityCoefficient * loan.getSum();

        for(int i = endDate; i < duration; ++i) {
            double currInterest = currBalance*rate;
            double currCredit = currMonthPay - currInterest;

            balance.set(i, currBalance);
            monthlyPay.set(i, currMonthPay);
            interest.set(i, currInterest);
            credit.set(i, currCredit);

            currBalance -= currCredit;
        }
    }
}
