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
    public String ID = "";
    public static String ip = "http://138.68.14.231:3030/";
    public static String apicode = "4089708b-a883-4e0a-b922-4035b9b3e579";
    public String walletname = "";
    public String addr = "";

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
                /*
                    if a user decides to go back to the login screen without making an account
                */
                LoginForm lf = new LoginForm();

                dispose();
            }
        });

        /*
        Creates a new wallet that asks for a name, password, email, and a wallet name, saves this to a .wallet file / text file along with the unique ID
         */

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Creating account...");

                name = textField1.getText();
                name = name.trim();
                pass = passwordField1.getText();
                pass = pass.trim();
                email = textField3.getText();
                email = email.trim();

                walletname = textField2.getText();

                CreateWalletResponse wallet = null;

                if(name.isEmpty() || pass.isEmpty() || email.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Missing name, email, or pass");
                    return;
                }

                if(!name.isEmpty() && !pass.isEmpty() && !email.isEmpty()) {

                    System.out.println("Creating wallet...");

                    try {
                        wallet = Wallet.create(ip, pass, apicode, null, name, email);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    ID = wallet.getIdentifier();
                    addr = wallet.getAddress();

                    if(!walletname.isEmpty()) {
                        try {
                            createWalletFile(walletname);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            createWalletFile("wallet"+name+ID);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "This is your unique ID for your wallet, please save this: " + ID);
                }

            }

            private void createWalletFile(String wn) throws IOException {
                File walletfile = new File(GUI.database + "/" + wn + ".txt");

                if (!walletfile.exists()) {
                    walletfile.createNewFile();
                    Writer filewriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(walletfile), "utf-8"));
                    filewriter.write(name+"\n"+ID+"\n"+pass+"\n"+email+"\n"+addr+"\n");
                    filewriter.close();
                }
                else{
                    JOptionPane.showMessageDialog(null, "A wallet already exists with this filename...Choosing a different filename");
                    createWalletFile("wallet"+name+ID);
                }

            }
        });

    }

}
