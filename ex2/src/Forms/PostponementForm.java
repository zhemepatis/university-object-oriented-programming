package Forms;

import java.awt.event.ActionEvent;

public class PostponementForm extends Form {
    FormItem post;

    public PostponementForm() {
        super();
        panel.setLayout(null);

        post = new FormItem("veikia!", 10, 70);
        panel.add(post.label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
