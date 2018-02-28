package bitcoinapp;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class LoginForm extends JFrame{

    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JButton createAnAccountButton;
    private JPanel panel1;
    private JLabel usernameIDLabel;
    private JComboBox comboBox1;

    public LoginForm()
    {
        this.setTitle("bitcoin");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        ArrayList<String> filedir = new ArrayList<>();

        File[] temp = GUI.database.listFiles();

        for(File f : temp)
        {
            if(f.isFile())
                filedir.add(f.getName());

        }

        comboBox1.setModel(new DefaultComboBoxModel(filedir.toArray()));
        comboBox1.setEditable(true);

        createAnAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterForm rf = new RegisterForm();
                System.out.println("Registering...");
                dispose();
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                /*
                User selects a wallet file and then logs in with said wallet file's correct user id and pass
                 */

                WalletForm wf = new WalletForm();
                dispose();
            }
        });
    }


}