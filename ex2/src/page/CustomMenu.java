package page;

import main.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMenu extends JMenuBar implements ActionListener {
    private WindowManager wm;

    private static JMenu loanMenu;
    private static JMenu viewMenu;

    private static JMenuItem postItem;
    private static JMenuItem showTableItem;
    private static JMenuItem showChartItem;
    private static JMenuItem exportItem;

    public CustomMenu(WindowManager wm) {
        this.wm = wm;

        // loan menu
        loanMenu = new JMenu("Loan");

        postItem = new JMenuItem("Postpone");
        postItem.addActionListener(this);
        disablePostItem();

        loanMenu.add(postItem);
        add(loanMenu);

        // view menu
        viewMenu = new JMenu("View");

        showTableItem = new JMenuItem("Show Table View");
        showTableItem.addActionListener(this);
        disableShowTableItem();

        showChartItem = new JMenuItem("Show Chart View");
        showChartItem.addActionListener(this);
        disableShowChartItem();

        exportItem = new JMenuItem("Export Payment Schedule");
        exportItem.addActionListener(this);
        disableExportItem();

        viewMenu.add(showTableItem);
        viewMenu.add(showChartItem);
        viewMenu.add(exportItem);

        add(viewMenu);
    }

    public void disablePostItem() {
        postItem.setEnabled(false);
    }

    public void disableShowTableItem() {
        showTableItem.setEnabled(false);
    }

    public void disableShowChartItem() {
        showChartItem.setEnabled(false);
    }

    public void disableExportItem() {
        exportItem.setEnabled(false);
    }

    public void enablePostItem() {
        postItem.setEnabled(true);
    }

    public void enableShowTableItem() {
        showTableItem.setEnabled(true);
    }

    public void enableShowChartItem() {
        showChartItem.setEnabled(true);
    }

    public void enableExportItem() {
        exportItem.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == postItem) {
            wm.showPostForm();
        }
        else if(e.getSource() == showTableItem) {
            wm.showTableView();
        }
        else if(e.getSource() == showChartItem) {
            wm.showChartView();
        }
        else if(e.getSource() == exportItem) {
            if(wm.loan.getRepaymentSchedule() == 0) {
                wm.aSchedule.exportToFile();
            }
            else {
                wm.lSchedule.exportToFile();
            }
        }
    }
}
