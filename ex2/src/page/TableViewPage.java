package page;

import com.sun.tools.jconsole.JConsoleContext;
import loan.AnnuityRepaymentSchedule;
import loan.LinearRepaymentSchedule;
import loan.Loan;
import loan.RepaymentSchedule;
import main.WindowManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableViewPage extends Page {
    JTable table;
    DefaultTableModel tableModel;

    public TableViewPage(WindowManager wm) {
        super(wm);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(getWidth()/2, getHeight()-60));

        tableModel.addColumn("Month");
        tableModel.addColumn("Balance");
        tableModel.addColumn("Monthly pay");
        tableModel.addColumn("Interest");
        tableModel.addColumn("Credit");

        contentPanel.add(sp);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    public void fillTable(Loan loan, RepaymentSchedule schedule) {
        int rows = schedule.duration;

        for(int i = 0; i < rows; ++i) {
            String currYear = String.valueOf(i/12+1);
            String currMonth = String.valueOf(i%12+1);

            tableModel.addRow(new Object[]{
                currYear + " year, " + currMonth + " month",
                String.valueOf(schedule.getBalance(i)),
                String.valueOf(schedule.getMonthlyPay(i)),
                String.valueOf(schedule.getInterest(i)),
                String.valueOf(schedule.getCredit(i))
            });
        }
    }

    public void updateTable(Loan loan, RepaymentSchedule schedule) {
        tableModel.setRowCount(0);
        fillTable(loan, schedule);
    }


}
