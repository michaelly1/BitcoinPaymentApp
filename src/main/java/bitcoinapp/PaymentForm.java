package bitcoinapp;

import info.blockchain.api.wallet.Wallet;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaymentForm extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextField textField2;

    public PaymentForm(User user, Wallet wallet) {

        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }
}
