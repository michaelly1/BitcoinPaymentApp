package bitcoinapp;

/*
Screen after logging into the app, should show wallet, addresses, balance, etc...
 */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class WalletForm extends JFrame{

    private JPanel panel1;
    private JButton logoutButton;
    public File walletfile;
    public String name;
    public String ID;
    public String pass;
    public String email;
    public ArrayList<String> addrs;

    public WalletForm(File selectwallet) throws Exception {
        walletfile = selectwallet;
        this.setTitle("bitcoin");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        BufferedReader r = null;

        r = new BufferedReader(new FileReader(selectwallet));
        name = r.readLine();
        ID = r.readLine();
        pass = r.readLine();
        email = r.readLine();

        String temp = r.readLine();
        while(!temp.isEmpty())
        {
            addrs.add(temp);
        }



    }


}
