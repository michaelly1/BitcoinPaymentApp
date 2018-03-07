package bitcoinapp;

import info.blockchain.api.APIException;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.PaymentResponse;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PaymentForm extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextField textField2;
    private String addr = "";
    private String amount = "";
    private User user = null;
    private Wallet wallet = null;

    public PaymentForm(User u, final Wallet w) {
        this.user = user;
        this.wallet = w;

        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addr = textField1.getText();
                amount = textField2.getText();
                long amounttoSAT = (long) (Float.parseFloat(amount) * 100000000);
                try {
                    if(wallet.getBalance() <= amounttoSAT)
                    {
                        JOptionPane.showMessageDialog(null,"Insufficent funds...");
                        return;
                    }
                    else
                    {
                        PaymentResponse paymentResponse = wallet.send(addr, amounttoSAT,null, null);
                        JOptionPane.showMessageDialog(null, paymentResponse.getMessage() + " " + paymentResponse.getNotice());
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
