package page;

import javax.swing.*;
import java.awt.*;

public class Page extends JFrame {
    CustomMenu menu;

    JPanel mainPanel;
    JPanel contentPanel;
    SpringLayout contentPanelLayout;

    public Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        contentPanel = new JPanel();
        contentPanelLayout = new SpringLayout();
        contentPanel.setLayout(contentPanelLayout);

        menu = new CustomMenu();
        mainPanel.add(menu, BorderLayout.NORTH);

        add(mainPanel);
    }

}
