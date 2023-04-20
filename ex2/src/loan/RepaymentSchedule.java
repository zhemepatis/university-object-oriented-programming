package loan;

public abstract class RepaymentSchedule {
    Loan loan;

    public RepaymentSchedule(Loan loan) {
        this.loan = loan;
    }

    public abstract void createRepaymentSchedule();
}
