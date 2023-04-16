import Forms.LoanForm;
import MainPage.MainPage;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // --- WINDOW SETTINGS ---
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        // --- PANELS ---
        MainPage mainPanel = new MainPage();

        Menu menu = new Menu(mainPanel);
        mainPanel.add(menu, BorderLayout.NORTH);

        LoanForm loanPanel = new LoanForm();
        mainPanel.add(loanPanel);

        mainPanel.add(new JPanel(), BorderLayout.WEST);
        mainPanel.add(new JPanel(), BorderLayout.EAST);
        mainPanel.add(new JPanel(), BorderLayout.SOUTH);

        window.add(mainPanel);
        window.setVisible(true);
    }
}