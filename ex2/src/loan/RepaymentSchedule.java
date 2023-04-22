package loan;

import java.util.ArrayList;

public abstract class RepaymentSchedule {
    Loan loan;

    ArrayList<Double> monthlyPay;
    ArrayList<Double> credit;
    ArrayList<Double> balance;
    ArrayList<Double> interest;
    public int duration;

    public RepaymentSchedule(Loan loan) {
        this.loan = loan;
        this.duration = loan.getTerm();

        balance = new ArrayList<>();
        monthlyPay = new ArrayList<>();
        interest = new ArrayList<>();
        credit = new ArrayList<>();
    }

    public abstract void createRepaymentSchedule();
    public abstract void postponeRepayment(Loan loan);

    public double getMonthlyPay(int month) {
        return (double) Math.round(monthlyPay.get(month)*100)/100;
    }

    public double getBalance(int month) {
        return (double) Math.round(balance.get(month)*100)/100;
    }

    public double getInterest(int month) {
        return (double) Math.round(interest.get(month)*100)/100;
    }

    public double getCredit(int month) {
        return (double) Math.round(credit.get(month)*100)/100;
    }
}
