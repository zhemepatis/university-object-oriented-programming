package MainPage;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends MainPage {
    int cols, rows;
    JTable table;

    public TablePanel(int cols, int rows) {
        super();

        this.cols = cols;
        this.rows = rows;

        String[] columnNames = {"Name", "Age", "Student"};

        Object[][] data = {
                {"Ken", 5, false},
                {"Tom", 3, true},
                {"Susam", 2, false},
                {"Mark", 20,true},
                {"Joe", 10, false}
        };

        this.table = new JTable(data, columnNames);
        panel.add(table, BorderLayout.CENTER);
    }
}
