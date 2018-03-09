package bitcoinapp;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import info.blockchain.api.APIException;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.PaymentResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PaymentForm extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextField textField2;
    private JLabel label1;
    private JLabel balanceLabel;
    private JTextField textField3;
    private JPanel fee;
    private User user = null;
    private Wallet wallet = null;

    public PaymentForm(User u, final Wallet w) {
        this.user = user;
        this.wallet = w;

        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);

        JOptionPane.showMessageDialog(null, "Please check the average transaction fee at https://bitcoinfees.info/");

        Thread balthread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        label1.setText("" + (String.format("%.010f", (double) wallet.getBalance() / 100000000)));
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        balthread.start();

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String addr = textField1.getText();
                String amount = textField2.getText();
                String fee = textField3.getText();
                long feesat = (long) (Float.parseFloat(fee) * 100000000);
                long amounttoSAT = (long) (Float.parseFloat(amount) * 100000000);

                try {
                    if (wallet.getBalance() <= amounttoSAT + feesat) {
                        JOptionPane.showMessageDialog(null, "Insufficent funds...your balance must be greater than the sending amount AND fee");
                        return;
                    } else if (addr.isEmpty() || amount.isEmpty() || fee.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty amount or address...");
                        return;
                    } else {
                        PaymentResponse paymentResponse = wallet.send(addr, amounttoSAT, null, feesat);
                        JOptionPane.showMessageDialog(null, paymentResponse.getMessage());
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


}
