package bitcoinapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {

    public LoginForm lf;
    public static File database = new File(System.getProperty("user.dir")+"/wallet/");
    ;

    public GUI()
    {

        if(!database.exists())
        {
            database.mkdirs();
        }

        lf = new LoginForm();
    }

}
