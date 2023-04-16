package Forms;

import MainPage.MainPage;
import MainPage.TablePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanForm extends MainPage implements ActionListener {
    private FormItem loanSum;
    private FormItem loanTerm;
    private FormItem repaymentSchedule;
    private FormItem annualRate;
    private JButton submitButton;

    public LoanForm() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(new Insets(40, 180, 40, 180)));

        // loan sum
        loanSum = new FormItem("Loan sum");
        loanSum.addTextField();
        add(loanSum);

        // loan term
        loanTerm = new FormItem("Loan term");
        loanTerm.addTextField();
        String[] termUnits = {"years", "months"};
        loanTerm.addComboBox(termUnits);
        add(loanTerm);

        // loan repayment schedule
        repaymentSchedule = new FormItem("Repayment schedule");
        String[] scheduleTypes = {"Annuity", "Linear"};
        repaymentSchedule.addComboBox(scheduleTypes);
        add(repaymentSchedule);

        // annual percentage rate
        annualRate = new FormItem("Annual rate (%)");
        annualRate.addTextField();
        add(annualRate);

        submitButton = new JButton("Submit");
        submitButton.setBounds(10, 180, 85, 20);
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.showTableView();
    }
}
