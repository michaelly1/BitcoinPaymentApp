package bitcoinapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {

    public LoginForm lf;
    public static File database = new File(System.getProperty("user.dir")+"/bitcoinapp/wallet");

    public GUI()
    {

        if(!database.exists())
        {
            System.out.println("Making dir...");
            database.mkdirs();
        }

        System.out.println("Starting UI...");

        lf = new LoginForm();
    }

}
