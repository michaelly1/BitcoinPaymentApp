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
        this.setLocationRelativeTo(null);

        Runnable balancerun = () ->{
            while(true)
            {
                try {
                    this.label1.setText(""+wallet.getBalance());
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread balthread = new Thread(balancerun);
        balthread.start();

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String addr = textField1.getText();
                String amount = textField2.getText();
                String fee = textField3.getText();
                long feesat = (long) (Float.parseFloat(fee)*100000000);
                long amounttoSAT = (long) (Float.parseFloat(amount) * 100000000);

                try {
                    if(wallet.getBalance() <= amounttoSAT+1000L)
                    {
                        JOptionPane.showMessageDialog(null,"Insufficent funds...");
                        return;
                    }
                    else if(addr.isEmpty() || amount.isEmpty() || fee.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"Empty amount or address...");
                        return;
                    }
                    else
                    {
                        PaymentResponse paymentResponse = wallet.send(addr, amounttoSAT,null, feesat);
                        JOptionPane.showMessageDialog(null, paymentResponse.getMessage() + " " + paymentResponse.getNotice());
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


}
