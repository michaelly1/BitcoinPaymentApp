package bitcoinapp;

/*
Screen after logging into the app, should show wallet, addresses, balance, etc...
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import info.blockchain.api.blockexplorer.entity.Input;
import info.blockchain.api.blockexplorer.entity.Output;
import info.blockchain.api.blockexplorer.entity.Transaction;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.Address;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WalletForm extends JFrame{

    private JPanel panel1;
    private JButton logoutButton;
    private JButton sendButton;
    private JLabel label1;
    private JLabel label2;
    private JList list1;
    private JList list2;
    public File walletfile;
    public String name;
    public String ID;
    public String pass;
    public String email;
    public ArrayList<String> addrs;
    public Wallet wallet = null;
    public User user = null;
    private List<Transaction> transactions;


    public WalletForm(File selectwallet, Wallet w, User u) throws Exception {
        walletfile = selectwallet;
        this.wallet = w;
        this.user = u;

        this.setTitle("bitcoin");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        BufferedReader r = new BufferedReader(new FileReader(selectwallet));

        name = r.readLine();
        ID = r.readLine();
        pass = r.readLine();
        email = r.readLine();

        String temp = r.readLine();

        while(temp != null && temp.isEmpty())
        {
            addrs.add(temp);
        }

        /*
        opens up the payment form to send transactions
         */

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PaymentForm payform = new PaymentForm(user,wallet);

            }
        });

        /*
        Should set everything to null and log out
         */
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                user = null;
                wallet = null;
                transactions = null;
                
                LoginForm lf = new LoginForm();
                dispose();

            }
        });
    }


}
