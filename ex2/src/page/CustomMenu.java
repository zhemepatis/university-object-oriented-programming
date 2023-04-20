package page;

import main.WindowManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMenu extends JMenuBar implements ActionListener {
    WindowManager wm;

    JMenu loanMenu;
    JMenu tableMenu;

    JMenuItem registerItem;
    JMenuItem postItem;
    JMenuItem showTableItem;
    JMenuItem toCsvItem;
    JMenuItem toXlsxItem;

    public CustomMenu(WindowManager wm) {
        this.wm = wm;

        // loan menu
        loanMenu = new JMenu("Loan");

        registerItem = new JMenuItem("Register");
        registerItem.addActionListener(this);
        postItem = new JMenuItem("Postpone");
        postItem.addActionListener(this);

        loanMenu.add(registerItem);
        loanMenu.add(postItem);
        add(loanMenu);

        // table menu
        tableMenu = new JMenu("Table");

        showTableItem = new JMenuItem("Show table");
        showTableItem.addActionListener(this);
        toCsvItem = new JMenuItem("Export to .csv file");
        toCsvItem.addActionListener(this);
        toXlsxItem = new JMenuItem("Export to .xlsx file");
        toXlsxItem.addActionListener(this);

        tableMenu.add(showTableItem);
        tableMenu.add(toXlsxItem);
        tableMenu.add(toCsvItem);
        add(tableMenu);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showTableItem) {
            wm.showTableView();
        }
        else if(e.getSource() == registerItem) {
            wm.showLoanForm();
        }
        else if(e.getSource() == postItem) {

        }
        else if(e.getSource() == toCsvItem) {

        }
        else if(e.getSource() == toXlsxItem) {

        }
    }
}
