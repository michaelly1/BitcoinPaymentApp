package bitcoinapp;

import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JFrame{

    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JButton createAnAccountButton;
    private JPanel panel1;
    private JLabel usernameIDLabel;

    public LoginForm()
    {
        this.setTitle("bitcoin");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        createAnAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterForm rf = new RegisterForm();
                dispose();
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                /*
                    when a user doesn't register for a new account
                    checks a database or file of IDs and passwords to login
                 */

                WalletForm wf = new WalletForm();
                dispose();
            }
        });
    }


}