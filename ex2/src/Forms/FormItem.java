package Forms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormItem extends JPanel {
    final int MARGIN = 5;

    final int LABEL_WIDTH = 180;
    final int LABEL_HEIGHT = 25;
    final int FIELD_WIDTH = 165;
    final int FIELD_HEIGHT = 25;
    final int BOX_WIDTH = 80;
    final int BOX_HEIGHT = 25;

    JPanel labelPanel;
    JPanel fieldPanel;
    JPanel boxPanel;

    JLabel label;
    JTextField field;
    JComboBox box;

    public FormItem(String label) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(new Insets(MARGIN, MARGIN, MARGIN, MARGIN)));

        labelPanel = new JPanel(new FlowLayout());
        labelPanel.setBorder(new EmptyBorder(new Insets(MARGIN, MARGIN, MARGIN, MARGIN)));

        this.label = new JLabel(label);
        labelPanel.add(this.label, BorderLayout.NORTH);

        add(labelPanel);
    }

    public void addTextField() {
        fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBorder(new EmptyBorder(new Insets(MARGIN, MARGIN, MARGIN, MARGIN)));

        field = new JTextField();
        fieldPanel.add(field, BorderLayout.NORTH);

        add(fieldPanel);
    }

    public void addComboBox(String[] items) {
        boxPanel = new JPanel(new BorderLayout());
        boxPanel.setBorder(new EmptyBorder(new Insets(MARGIN, MARGIN, MARGIN, MARGIN)));

        box = new JComboBox(items);
        boxPanel.add(box, BorderLayout.NORTH);

        add(boxPanel);
    }
}
