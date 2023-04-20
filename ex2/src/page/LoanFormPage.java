package page;

import loan.AnnuityRepaymentSchedule;
import loan.Loan;
import main.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanFormPage extends Page implements ActionListener {
    Loan loan;

    private JLabel loanSumLabel;
    private JLabel loanTermLabel;
    private JLabel repaymentScheduleLabel;
    private JLabel annualRateLabel;
    private JTextField loanSumField;
    private JTextField loanTermField;
    private JTextField annualRateField;
    private JComboBox loanTermBox;
    private JComboBox repaymentScheduleBox;
    private JButton submitButton;

    public LoanFormPage(WindowManager wm, Loan loan) {
        super(wm);

        this.loan = loan;

        loanSumLabel = new JLabel("Loan sum");
        loanSumLabel.setPreferredSize(new Dimension(200, 15));
        loanSumField = new JTextField(7);
        contentPanelLayout.putConstraint(SpringLayout.WEST, loanSumLabel, 10, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, loanSumLabel, 10, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, loanSumField, 20, SpringLayout.EAST, loanSumLabel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, loanSumField, 10, SpringLayout.NORTH, contentPanel);
        contentPanel.add(loanSumLabel);
        contentPanel.add(loanSumField);

        loanTermLabel = new JLabel("Loan term");
        loanTermLabel.setPreferredSize(new Dimension(200, 15));
        loanTermField = new JTextField(7);
        String[] termUnits = {"years", "months"};
        loanTermBox = new JComboBox(termUnits);
        loanTermBox.setPreferredSize(new Dimension(80, 20));
        contentPanelLayout.putConstraint(SpringLayout.WEST, loanTermLabel, 10, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, loanTermLabel, 50, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, loanTermField, 20, SpringLayout.EAST, loanTermLabel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, loanTermField, 50, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, loanTermBox, 20, SpringLayout.EAST, loanTermField);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, loanTermBox, 50, SpringLayout.NORTH, contentPanel);
        contentPanel.add(loanTermLabel);
        contentPanel.add(loanTermField);
        contentPanel.add(loanTermBox);

        repaymentScheduleLabel = new JLabel("Loan repayment schedule");
        repaymentScheduleLabel.setPreferredSize(new Dimension(200, 15));
        String[] scheduleTypes = {"annuity", "linear"};
        repaymentScheduleBox = new JComboBox(scheduleTypes);
        repaymentScheduleBox.setPreferredSize(new Dimension(80, 20));
        contentPanelLayout.putConstraint(SpringLayout.WEST, repaymentScheduleLabel, 10, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, repaymentScheduleLabel, 90, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, repaymentScheduleBox, 20, SpringLayout.EAST, repaymentScheduleLabel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, repaymentScheduleBox, 90, SpringLayout.NORTH, contentPanel);
        contentPanel.add(repaymentScheduleLabel);
        contentPanel.add(repaymentScheduleBox);

        annualRateLabel = new JLabel("Annual percentage rate (%)");
        annualRateLabel.setPreferredSize(new Dimension(200, 15));
        annualRateField = new JTextField(7);
        contentPanelLayout.putConstraint(SpringLayout.WEST, annualRateLabel, 10, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, annualRateLabel, 130, SpringLayout.NORTH, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.WEST, annualRateField, 20, SpringLayout.EAST, annualRateLabel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, annualRateField, 130, SpringLayout.NORTH, contentPanel);
        contentPanel.add(annualRateLabel);
        contentPanel.add(annualRateField);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(85, 20));
        contentPanelLayout.putConstraint(SpringLayout.WEST, submitButton, 100, SpringLayout.WEST, contentPanel);
        contentPanelLayout.putConstraint(SpringLayout.NORTH, submitButton, 170, SpringLayout.NORTH, contentPanel);
        contentPanel.add(submitButton);
        submitButton.addActionListener(this);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loan.setSum(Double.parseDouble(loanSumField.getText()));
        int termInMonths = (loanTermBox.getSelectedIndex() == 0) ? Integer.parseInt(loanTermField.getText())*12 : Integer.parseInt(loanTermField.getText());
        loan.setTerm(termInMonths);
        loan.setRepaymentSchedule(repaymentScheduleBox.getSelectedIndex());
        loan.setAnnualRate(Double.parseDouble(annualRateField.getText())/100);

        AnnuityRepaymentSchedule schedule = new AnnuityRepaymentSchedule(loan);

        wm.showTableView();
    }
}
