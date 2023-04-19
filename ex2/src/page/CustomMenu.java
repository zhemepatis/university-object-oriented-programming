package page;

import javax.swing.*;

public class CustomMenu extends JMenuBar {
    JMenu loanMenu;
    JMenu tableMenu;

    JMenuItem registerItem;
    JMenuItem postItem;
    JMenuItem showTableItem;
    JMenuItem toCsvItem;
    JMenuItem toXlsxItem;

    public CustomMenu() {
        // loan menu
        loanMenu = new JMenu("Loan");

        registerItem = new JMenuItem("Register");
        postItem = new JMenuItem("Postpone");

        loanMenu.add(registerItem);
        loanMenu.add(postItem);
        add(loanMenu);

        // table menu
        tableMenu = new JMenu("Table");

        showTableItem = new JMenuItem("Show table");
        toCsvItem = new JMenuItem("Export to .csv file");
        toXlsxItem = new JMenuItem("Export to .xlsx file");

        tableMenu.add(showTableItem);
        tableMenu.add(toXlsxItem);
        tableMenu.add(toCsvItem);
        add(tableMenu);
    }
}
