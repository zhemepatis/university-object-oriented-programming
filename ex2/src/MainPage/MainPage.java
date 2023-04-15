package MainPage;

import javax.swing.*;
import java.awt.*;

public class MainPage {
    public JPanel panel;
    JMenuBar menuBar;

    public MainPage() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.green);

        menuBar = new JMenuBar();

        // loan menu
        JMenu loanMenu = new JMenu("Loan");
        JMenuItem postItem = new JMenuItem("Postpone");

        loanMenu.add(postItem);
        menuBar.add(loanMenu);

        // table menu
        JMenu tableMenu = new JMenu("Table");
        JMenuItem toCsvItem = new JMenuItem("Export to .csv file");

        JMenuItem toXlsxItem = new JMenuItem("Export to .xlsx file");
        tableMenu.add(toXlsxItem);
        tableMenu.add(toCsvItem);
        menuBar.add(tableMenu);

        // adding menu bar to the menu panel
        panel.add(menuBar, BorderLayout.NORTH);
    }
}
