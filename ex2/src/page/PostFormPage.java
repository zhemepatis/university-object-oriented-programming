package page;

import loan.Loan;
import main.WindowManager;

import javax.swing.*;
import java.awt.*;

public class PostFormPage extends Page {
    Loan loan;

    private JLabel postDateLabel;
    private JLabel postTermLabel;
    private JLabel postRateLabel;
    private JTextField postTermField;
    private JTextField postRateField;
    private JComboBox postDateBox;
    private JComboBox postTermBox;

    public PostFormPage(WindowManager wm, Loan loan) {
        super(wm);
        this.loan = loan;

        // postponement date
        postDateLabel = new JLabel("Postponement date");
        postDateLabel.setPreferredSize(new Dimension(200, 15));
        String[] dates = new String[loan.getTerm()];
        for(int i = 0; i < loan.getTerm(); ++i)
            dates[i] = (i/12+1) + " year, " + (i%12+1) + " month";
        postDateBox = new JComboBox(dates);
        postDateBox.setPreferredSize(new Dimension(130, 20));
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postDateLabel, 10,  SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postDateLabel, 10,  SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postDateBox, 10,  SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postDateBox, 20,  SpringLayout.EAST, postDateLabel);
        contentPanel.add(postDateLabel);
        contentPanel.add(postDateBox);

        // postponement term
        postTermLabel = new JLabel("Postponement term");
        postTermLabel.setPreferredSize(new Dimension(200, 15));
        postTermField = new JTextField(7);
        String[] termUnits = {"years", "months"};
        postTermBox = new JComboBox(termUnits);
        postTermBox.setPreferredSize(new Dimension(80, 20));
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postTermLabel, 50,  SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postTermLabel, 10,  SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postTermField, 50,  SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postTermField, 20,  SpringLayout.EAST, postTermLabel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postTermBox, 50,  SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postTermBox, 20,  SpringLayout.EAST, postTermField);
        contentPanel.add(postTermLabel);
        contentPanel.add(postTermField);
        contentPanel.add(postTermBox);

        mainPanel.add(contentPanel);
        add(mainPanel);
    }
}
