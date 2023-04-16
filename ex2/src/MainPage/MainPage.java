package MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JPanel{
    public MainPage() {
        setLayout(new BorderLayout());
    }

    public void showTableView() {
        System.out.println("Showing table view");
    }

    public void showGraphView() {
        System.out.println("Showing graph view");
    }
}
