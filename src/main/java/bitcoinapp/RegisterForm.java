package bitcoinapp;

import info.blockchain.api.APIException;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.CreateWalletResponse;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterForm extends JFrame{
    private JTextField textField1;
    private JTextField textField3;
    private JButton registerButton;
    private JButton loginButton;
    private JPanel rfpanel;
    private JPasswordField passwordField1;
    private JTextField textField2;
    public String name;
    public String pass;
    public String email;
    public String ID;
    public String ip = "http://138.68.14.231:3030/";
    public String walletname;
    public String addr;

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
                if(email != null && pass != null)
                    name = textField1.getText();

                if(name != null && email != null)
                    pass = passwordField1.getText();

                if(pass != null && name != null)
                    email = textField3.getText();

                walletname = textField2.getText();

                CreateWalletResponse wallet = null;

                if(name != null && pass != null) {
                    try {
                        wallet = Wallet.create(ip, pass, "4089708b-a883-4e0a-b922-4035b9b3e579", null, name, email);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    ID = wallet.getIdentifier();
                    addr = wallet.getAddress();

                    if(walletname != null) {
                        try {
                            createWalletFile(walletname);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    else
                    {
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

                        try {
                            createWalletFile("bitcoin"+timeStamp);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "This is your unique ID for your wallet, please save this: " + ID);
                }

            }

            private void createWalletFile(String wn) throws IOException {
                File walletfile = new File(GUI.database + wn + ".wallet");

                if (!walletfile.exists()) {
                    walletfile.mkdir();
                    Writer filewriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(walletfile), "utf-8"));
                    filewriter.write(name+"\n"+pass+"\n"+email+"\n"+ID+"\n"+addr+"\n");
                }
                else{
                    JOptionPane.showMessageDialog(null, "A wallet already exists with this filename...");
                }

            }
        });

    }

}
