import Forms.FormItem;
import Forms.LoanForm;
import Forms.PostponementForm;
import MainPage.TablePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // --- WINDOW SETTINGS ---
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        // --- PANELS ---
//        JPanel loanPanel = new LoanForm().panel;
//        window.add(loanPanel);

//        JPanel postponementPanel = new PostponementForm().panel;
//        window.add(postponementPanel);

        JPanel tablePage = new TablePanel(10, 20).panel;
        window.add(tablePage);

        window.setVisible(true);
    }
}