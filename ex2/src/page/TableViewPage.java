package page;

import loan.Loan;
import main.WindowManager;

import javax.swing.*;

public class TableViewPage extends Page {
    WindowManager wm;

    final String[] columnNames = {"Month", "Amount"};

    JTable table;

    public TableViewPage(WindowManager wm) {
        super(wm);
        this.wm = wm;


    }

}
