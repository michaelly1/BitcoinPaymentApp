package bitcoinapp;

/*
Screen after logging into the app, should show wallet, addresses, balance, etc...
 */

import javax.swing.*;

public class WalletForm extends JFrame{

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public WalletForm()
    {
        this.setTitle("bitcoin");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
