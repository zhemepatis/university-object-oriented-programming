package main;

import loan.AnnuityRepaymentSchedule;
import loan.LinearRepaymentSchedule;
import loan.Loan;
import loan.RepaymentSchedule;
import page.LoanFormPage;
import page.TableViewPage;

public class WindowManager {
    public LoanFormPage lf;
    public TableViewPage tv;

    Loan loan;
    public AnnuityRepaymentSchedule aSchedule;
    public LinearRepaymentSchedule lSchedule;

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
        tv.menu.enableShowChartItem();
        tv.menu.disableShowTableItem();

        lf.setVisible(false);
        tv.setVisible(true);
    }
}
