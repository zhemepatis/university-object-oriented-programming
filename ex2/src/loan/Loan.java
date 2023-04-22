package loan;

public class Loan {
    private double sum;
    private int term, postTerm;
    private int repaymentSchedule;
    private double annualRate, postRate;
    private int postDate;

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

    public void setPostTerm(int postTerm) {this.postTerm = postTerm;}

    public void setPostDate(int postDate) {this.postDate = postDate;}

    public void setPostRate(double postRate) {this.postRate = postRate;}

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

    public int getPostTerm() {return postTerm;}

    public int getPostDate() {return postDate;}

    public double getPostRate() {return postRate;}
}
