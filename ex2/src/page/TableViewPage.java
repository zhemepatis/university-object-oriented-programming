package page;

import loan.AnnuityRepaymentSchedule;
import loan.LinearRepaymentSchedule;
import loan.Loan;
import loan.RepaymentSchedule;
import main.WindowManager;

import javax.swing.*;
import java.awt.*;

public class TableViewPage extends Page {
    int COLUMN_NUM = 5;
    int ROW_NUM;
    final String[] COLUMN_NAMES = {"Month", "Balance", "Monthly pay", "Interest", "Credit"};
    String[][] data;

    WindowManager wm;
    JTable table;

    public TableViewPage(WindowManager wm) {
        super(wm);
        this.wm = wm;
    }

    public void createTable(Loan loan, AnnuityRepaymentSchedule schedule) {
        this.ROW_NUM = loan.getTerm();

        createTableData(schedule);
        table = new JTable(data, COLUMN_NAMES);
        JScrollPane sp = new JScrollPane(table);
//        table.setEnabled(false);

        contentPanel.add(sp);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    public void createTable(Loan loan, LinearRepaymentSchedule schedule) {
        this.ROW_NUM = loan.getTerm();

        createTableData(schedule);
        table = new JTable(data, COLUMN_NAMES);

        contentPanel.add(table, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    void createTableData(AnnuityRepaymentSchedule schedule) {
        data = new String[ROW_NUM][COLUMN_NUM];

        for(int i = 0; i < ROW_NUM; ++i) {
                data[i][0] = String.valueOf(i%12+1);
                data[i][1] = String.valueOf(schedule.getBalance(i));
                data[i][2] = String.valueOf(schedule.getMonthlyPay());
                data[i][3] = String.valueOf(schedule.getInterest(i));
                data[i][4] = String.valueOf(schedule.getCredit(i));
        }
    }

    void createTableData(LinearRepaymentSchedule schedule) {
        data = new String[COLUMN_NUM][table.getRowCount()];
    }

}
