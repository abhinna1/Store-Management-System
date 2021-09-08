package SMS.InsertPage;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class InsertPage {
    JFrame frame;
    JPanel panel;
    JLabel title;
    JLabel nameLbl;
    JTextField nameent;
    JLabel CPLbl;
    JTextField CPEnt;
    JLabel SCPEnt;
    JTextField SPEND;
    InsertPage(){
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(null);
        frame.setBounds(0, 0, 300,400);
        panel.setBounds(0, 0, 300, 400);
        frame.add(panel);

        title = new JLabel("Add Products");
        title.setBounds(100, 10, 100, 100);
        panel.add(title);

        nameLbl = new JLabel("Product Name");
        nameent = new JTextField(100);
        nameLbl.setBounds(10, 30, 200, 20);
        nameent.setBounds(100, 30, 100, 20);
        panel.add(nameLbl);
        panel.add(nameent);

        panel.setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new InsertPage();

    }
}
