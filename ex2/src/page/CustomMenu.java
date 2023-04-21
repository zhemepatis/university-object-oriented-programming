package page;

import main.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMenu extends JMenuBar implements ActionListener {
    private static WindowManager wm;

    private static JMenu loanMenu;
    private static JMenu viewMenu;

    private static JMenuItem postItem;
    private static JMenuItem showTableItem;
    private static JMenuItem showChartItem;

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

        viewMenu.add(showTableItem);
        viewMenu.add(showChartItem);
        add(viewMenu);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showTableItem) {
            wm.showTableView();
        }
        else if(e.getSource() == postItem) {

        }
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


    public void enablePostItem() {
        postItem.setEnabled(true);
    }

    public void enableShowTableItem() {
        showTableItem.setEnabled(true);
    }

    public void enableShowChartItem() {
        showChartItem.setEnabled(true);
    }
}
