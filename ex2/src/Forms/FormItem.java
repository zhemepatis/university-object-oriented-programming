package Forms;

import javax.swing.*;
import java.awt.*;

public class FormItem {
    final int MARGIN = 5;

    final int LABEL_WIDTH = 180;
    final int LABEL_HEIGHT = 25;
    final int FIELD_WIDTH = 165;
    final int FIELD_HEIGHT = 25;
    final int BOX_WIDTH = 80;
    final int BOX_HEIGHT = 25;

    int width;
    int x, y;

    JLabel label;
    JTextField field;
    JComboBox box;

    public FormItem(String label, int x, int y) {
        this.label = new JLabel(label);
        this.x = x;
        this.y = y;
        this.label.setBounds(this.x, this.y, LABEL_WIDTH, LABEL_HEIGHT);

        width = LABEL_WIDTH + MARGIN;
    }

    public void addTextField() {
        field = new JTextField();
        field.setBounds(width, y, FIELD_WIDTH, FIELD_HEIGHT);

        width += LABEL_WIDTH + MARGIN;
    }

    public void addComboBox(String[] items) {
        box = new JComboBox(items);
        box.setBounds(width, y, BOX_WIDTH, BOX_HEIGHT);

        width += BOX_WIDTH + MARGIN;
    }
}
