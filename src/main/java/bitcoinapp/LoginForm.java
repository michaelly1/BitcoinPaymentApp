package bitcoinapp;

import info.blockchain.api.wallet.Wallet;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
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
        this.setLocationRelativeTo(null);
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

                String un = textField1.getText();
                String pass = passwordField1.getText();

                if(un.isEmpty() || pass.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Empty username or password");
                    return;
                }

                File selectwallet = new File(GUI.database+ "/" + (String) comboBox1.getSelectedItem());

                BufferedReader r = null;

                try {
                    r = new BufferedReader(new FileReader(selectwallet));
                    String fileUN = r.readLine();
                    String fileID = r.readLine();
                    String filePass = r.readLine();

                    if(un.equals(fileUN) || un.equals(fileID))
                    {
                        if(pass.equals(filePass))
                        {
                            User user = new User(fileID, filePass);
                            Wallet wallet = new Wallet(RegisterForm.ip, RegisterForm.apicode, fileID, filePass);
                            WalletForm wf = new WalletForm(selectwallet, wallet, user);
                            dispose();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Incorrect username/id or password");
                        return;
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
    }


}