package bitcoinapp;

import info.blockchain.api.APIException;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.CreateWalletResponse;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class RegisterForm extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton registerButton;
    private JButton loginButton;
    private JPanel rfpanel;
    public String name;
    public String pass;
    public String email;
    public String ID;


    /*
    Register form/screen, should be done?
    Need to have external server for wallet creation service
     */

    public RegisterForm()
    {
        this.setTitle("bitcoin");
        this.setContentPane(rfpanel);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginForm lf = new LoginForm(name, pass, email, ID);
                dispose();
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name = textField1.getText();
                pass = textField2.getText();
                email = textField3.getText();

                CreateWalletResponse wallet = null;
                try {
                    wallet = Wallet.create("http://localhost:3000/", pass, "4089708b-a883-4e0a-b922-4035b9b3e579", null, name, email);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (APIException e1) {
                    e1.printStackTrace();
                }
                ID = wallet.getIdentifier();
                JOptionPane.showMessageDialog(null, "This is your unique ID for your wallet, please save this: " + ID);

            }
        });
    }


}
