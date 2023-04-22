package page;

import loan.Loan;
import main.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostFormPage extends Page implements ActionListener {
    Loan loan;

    private JLabel postDateLabel;
    private JLabel postTermLabel;
    private JLabel postRateLabel;
    private JTextField postTermField;
    private JTextField postRateField;
    private JComboBox postDateBox;
    private JComboBox postTermBox;
    private JButton submitButton;

    public PostFormPage(WindowManager wm, Loan loan) {
        super(wm);
        this.loan = loan;

        // postponement date
        postDateLabel = new JLabel("Postponement date");
        postDateLabel.setPreferredSize(new Dimension(150, 15));
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
        postTermLabel.setPreferredSize(new Dimension(150, 15));
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

        // postponement rate
        postRateLabel = new JLabel("Postponement rate (%)");
        postRateLabel.setPreferredSize(new Dimension(150, 15));
        postRateField = new JTextField(7);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postRateLabel, 90, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postRateLabel, 10, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, postRateField, 90, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, postRateField, 20, SpringLayout.EAST, postRateLabel);
        contentPanel.add(postRateLabel);
        contentPanel.add(postRateField);

        // submit button
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(85, 20));
        contentPanelLayout.putConstraint(SpringLayout.WEST, submitButton, 100, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, submitButton, 130, SpringLayout.NORTH, contentPanel);
        contentPanel.add(submitButton);
        submitButton.addActionListener(this);

        mainPanel.add(contentPanel);
        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loan.setPostDate(postDateBox.getSelectedIndex());
        int term = (postTermBox.getSelectedIndex() == 0) ? Integer.parseInt(postTermField.getText())*12 : Integer.parseInt(postTermField.getText());
        loan.setPostTerm(term);
        loan.setPostRate(Double.parseDouble(postRateField.getText())/100);

        wm.aSchedule.postponeRepayment(loan);
        wm.lSchedule.postponeRepayment(loan);

        if(loan.getRepaymentSchedule() == 0)
            wm.tv.updateTable(loan, wm.aSchedule);
        else
            wm.tv.updateTable(loan, wm.lSchedule);

        setVisible(false);
        wm.showTableView();
    }
}
