package main;

import loan.AnnuityRepaymentSchedule;
import loan.LinearRepaymentSchedule;
import loan.Loan;
import loan.RepaymentSchedule;
import page.LoanFormPage;
import page.PostFormPage;
import page.TableViewPage;

public class WindowManager {
    public LoanFormPage lf;
    public PostFormPage pf;
    public TableViewPage tv;

    Loan loan;
    public AnnuityRepaymentSchedule aSchedule;
    public LinearRepaymentSchedule lSchedule;

    public WindowManager() {
        loan = new Loan();

        lf = new LoanFormPage(this, loan);
        tv = new TableViewPage(this);

        lf.setVisible(true);
    }

    public static void main(String[] args) {
        WindowManager wm  = new WindowManager();
        wm.showLoanForm();
    }

    public void showLoanForm() {
        lf.setVisible(true);
    }

    public void showPostForm() {
        pf = new PostFormPage(this, loan);

        tv.setVisible(false);
        pf.setVisible(true);
    }

    public void showTableView() {
        tv.menu.enableExportItem();
        tv.menu.enableShowChartItem();
        tv.menu.disableShowTableItem();

        lf.setVisible(false);
        tv.setVisible(true);
    }

    public void showChartView() {
        tv.menu.enableExportItem();
        tv.menu.enableShowTableItem();
        tv.menu.disableShowChartItem();

    }
}
