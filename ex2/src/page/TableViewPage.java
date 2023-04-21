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

    JTable table;

    final String[] COLUMN_NAMES = {"Month", "Balance", "Monthly pay", "Interest", "Credit"};
    String[][] data;

    public TableViewPage(WindowManager wm) {
        super(wm);
    }

    public void createTable(Loan loan, AnnuityRepaymentSchedule schedule) {
        this.ROW_NUM = loan.getTerm();

        createTableData(schedule);
        table = new JTable(data, COLUMN_NAMES);
        JScrollPane sp = new JScrollPane(table);

        contentPanel.add(sp);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    public void createTable(Loan loan, LinearRepaymentSchedule schedule) {
        this.ROW_NUM = loan.getTerm();

        createTableData(schedule);
        table = new JTable(data, COLUMN_NAMES);
        JScrollPane sp = new JScrollPane(table);

        contentPanel.add(sp);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    void createTableData(AnnuityRepaymentSchedule schedule) {
        data = new String[ROW_NUM][COLUMN_NUM];

        for(int i = 0; i < ROW_NUM; ++i) {
            data[i][0] = (i/12+1) + " year, " + (i%12+1) + " month";
            data[i][1] = String.valueOf(schedule.getBalance(i));
            data[i][2] = String.valueOf(schedule.getMonthlyPay());
            data[i][3] = String.valueOf(schedule.getInterest(i));
            data[i][4] = String.valueOf(schedule.getCredit(i));
        }
    }

    void createTableData(LinearRepaymentSchedule schedule) {
        data = new String[ROW_NUM][COLUMN_NUM];

        for(int i = 0; i < ROW_NUM; ++i) {
            data[i][0] = (i/12+1) + " year, " + (i%12+1) + " month";
            data[i][1] = String.valueOf(schedule.getBalance(i));
            data[i][2] = String.valueOf(schedule.getMonthlyPay(i));
            data[i][3] = String.valueOf(schedule.getInterest(i));
            data[i][4] = String.valueOf(schedule.getCredit());
        }
    }

}
