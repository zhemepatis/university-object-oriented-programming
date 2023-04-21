package page;

import main.WindowManager;

import javax.swing.*;
import java.awt.*;

public class Page extends JFrame {
    final int WIDTH = 700;
    final int HEIGHT = 500;

    WindowManager wm;
    public CustomMenu menu;

    JPanel mainPanel;
    JPanel contentPanel;
    SpringLayout contentPanelLayout;

    public Page(WindowManager wm) {
        this.wm = wm;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        contentPanel = new JPanel();
        contentPanelLayout = new SpringLayout();
        contentPanel.setLayout(contentPanelLayout);

        menu = new CustomMenu(this.wm);
        mainPanel.add(menu, BorderLayout.NORTH);

        add(mainPanel);
    }

}
