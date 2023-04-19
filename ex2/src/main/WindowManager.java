package main;

import page.Loan;
import page.LoanFormPage;

public class WindowManager {
    LoanFormPage lf;
    Loan loan;

    public WindowManager() {
        loan = new Loan();

        lf = new LoanFormPage(this, loan);
        lf.setVisible(true);
    }

    public static void main(String[] args) {
        WindowManager wm  = new WindowManager();
        wm.showLoanForm();
    }

    void showLoanForm() {
        lf.setVisible(true);
    }

    void showTableView() {

    }

}
