package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Form extends JFrame implements ActionListener {
    public JPanel panel;

    public Form() {
        panel = new JPanel();
    }
}
