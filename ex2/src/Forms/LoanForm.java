package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoanForm extends Form {
    FormItem loanSum;
    FormItem loanTerm;
    FormItem repaymentSchedule;
    FormItem annualRate;
    JButton submitButton;

    public LoanForm() {
        super();
        panel.setLayout(null);

        // loan sum
        loanSum = new FormItem("Loan sum", 10, 20);
        loanSum.addTextField();

        panel.add(loanSum.label);
        panel.add(loanSum.field);

        // loan term
        loanTerm = new FormItem("Loan term", 10, 60);
        loanTerm.addTextField();
        String[] termUnits = {"years", "months"};
        loanTerm.addComboBox(termUnits);

        panel.add(loanTerm.label);
        panel.add(loanTerm.field);
        panel.add(loanTerm.box);

        // loan repayment schedule
        repaymentSchedule = new FormItem("Repayment schedule", 10, 100);
        String[] scheduleTypes = {"Annuity", "Linear"};
        repaymentSchedule.addComboBox(scheduleTypes);

        panel.add(repaymentSchedule.label);
        panel.add(repaymentSchedule.box);

        // annual percentage rate
        annualRate = new FormItem("Annual rate (%)", 10, 140);
        annualRate.addTextField();

        panel.add(annualRate.label);
        panel.add(annualRate.field);

        submitButton = new JButton("Submit");
        submitButton.setBounds(10, 180, 85, 20);
        submitButton.addActionListener(this);
        panel.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
