package loan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void exportToFile() {
        try{
            File output = new File("loan_details.txt");
            output.createNewFile();
            FileWriter fileWriter = new FileWriter("loan_details.txt");

            fileWriter.write("Loan sum: " + loan.getSum() + "\n");
            fileWriter.write("Loan term: " + loan.getTerm() + "\n");
            fileWriter.write("Annual rate: " + loan.getAnnualRate()*100 + "\n\n");

            fileWriter.write("Month / Balance / Monthly pay / Interest / Credit \n");
            for(int i = 0; i < duration; ++i) {
                String currYear = String.valueOf(i/12+1);
                String currMonth = String.valueOf(i%12+1);

                fileWriter.write(currYear + " year, " + currMonth + " month / " + getBalance(i) + " / " + getMonthlyPay(i) + " / " + getInterest(i) + " / " + getCredit(i) + "\n");
            }

            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
