package SMS;

import javax.swing.*;
import java.awt.*;

public class Home {
    JFrame frame;
    JPanel panel;
    JLabel title;
    JLabel searchlbl;
    JTextField searchBar;

    public void getHomePage(){
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Store Manager");
        searchlbl = new JLabel("Search Item");
        searchBar = new JTextField(10);

        frame.setBounds(0, 0, 200, 200);
        panel.setBounds(0, 0, 200, 200);
        frame.add(panel);

        title.setBounds(40, 0, 100, 50);
        title.setForeground(Color.RED);
        panel.add(title);

        searchlbl.setBounds(1, 50, 100, 20);
        searchBar.setBounds(80, 50, 100, 20);
        panel.add(searchBar);
        panel.add(searchlbl);


        // Frame settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.setVisible(true);
    }

}
