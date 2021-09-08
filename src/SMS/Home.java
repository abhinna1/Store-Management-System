package SMS;

// User-Defined-Classes
import dbConfig.Settings;

// Java-Classes
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Home {
    JFrame frame;
    JPanel panel;
    JLabel title;
    JLabel searchlbl;
    JTextField searchBar;
    JButton searchBtn;

    Settings settings;
    Connection con;

    String[][] data = {{"1", "Pepsi", "100", "120", "23-08-21"},
                        {"2", "Lays", "35", "40", "22-08-21"}};
    String[] head = {"SN", "Name", "CP", "SP", "Date"};
    private Listener listener;


    public void getHomePage(){
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Store Manager");
        searchlbl = new JLabel("Search Item");
        searchBar = new JTextField(80);
        searchBtn = new JButton("Search");
        settings = new Settings("jdbc:mysql://127.0.0.1:3306/storedb", "Abhinna", "$abhi123");
        con = settings.connectDb();
        listener = new Listener();

        frame.setBounds(0, 0, 400, 200);
        frame.setTitle("Store Manager");
        panel.setBounds(0, 0, 400, 200);
        panel.setBackground(Color.PINK);
        frame.add(panel);

        title.setBounds(120, 0, 400, 50);
        title.setForeground(Color.RED);
        title.setFont(title.getFont().deriveFont(20f));
        panel.add(title);

        searchlbl.setBounds(18, 55, 100, 20);
        searchlbl.setFont(searchlbl.getFont().deriveFont(15f));
        searchBar.setBounds(110, 50, 150, 30);
        searchBtn.setBounds(275, 55, 80, 20);
        searchBtn.addActionListener(listener);
        panel.add(searchBar);
        panel.add(searchlbl);
        panel.add(searchBtn);

        // Create Table
        ProductTable tb = new ProductTable(frame);
        JScrollPane tbl = tb.getTable();
        tbl.setBounds(15, 90, 350, 200);
        panel.add(tbl);



        // Frame settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.setVisible(true);

    }


}
