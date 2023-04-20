package main;

import loan.Loan;
import page.LoanFormPage;
import page.TableViewPage;

public class WindowManager {
    LoanFormPage lf;
    TableViewPage tv;
    Loan loan;

    public WindowManager() {
        loan = new Loan();

        lf = new LoanFormPage(this, loan);
        lf.setVisible(true);

        tv = new TableViewPage(this);
    }

    public static void main(String[] args) {
        WindowManager wm  = new WindowManager();
        wm.showLoanForm();
    }

    public void showLoanForm() {
        tv.setVisible(false);
        lf.setVisible(true);
    }

    public void showTableView() {
        lf.setVisible(false);
        tv.setVisible(true);
    }
}
