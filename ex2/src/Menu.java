import MainPage.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar implements ActionListener {
    MainPage mainPanel;

    private JMenu loanMenu;
    private JMenu tableMenu;

    private JMenuItem registerItem;
    private JMenuItem postItem;
    private JMenuItem showTableItem;
    private JMenuItem toCsvItem;
    private JMenuItem toXlsxItem;

    public Menu(MainPage mainPanel) {
        this.mainPanel = mainPanel;

        // loan menu
        loanMenu = new JMenu("Loan");

        registerItem = new JMenuItem("Register");
        postItem = new JMenuItem("Postpone");
        registerItem.addActionListener(this);
        postItem.addActionListener(this);

        loanMenu.add(registerItem);
        loanMenu.add(postItem);
        add(loanMenu);

        // table menu
        tableMenu = new JMenu("Table");

        showTableItem = new JMenuItem("Show table");
        toCsvItem = new JMenuItem("Export to .csv file");
        toXlsxItem = new JMenuItem("Export to .xlsx file");

        showTableItem.addActionListener(this);
        toXlsxItem.addActionListener(this);
        toCsvItem.addActionListener(this);

        tableMenu.add(showTableItem);
        tableMenu.add(toXlsxItem);
        tableMenu.add(toCsvItem);
        add(tableMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registerItem) {
            System.out.println("Register loan!");
        }
        else if(e.getSource() == postItem) {
            System.out.println("Postpone loan!");
        }
        else if(e.getSource() == showTableItem) {
            mainPanel.showTableView();
        }
        else if(e.getSource() == toXlsxItem) {
            System.out.println("XLSX!");
        }
        else if(e.getSource() == toCsvItem) {
            System.out.println("CSV!");
        }
    }
}
