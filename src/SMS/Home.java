package SMS;

// User-Defined-Classes

// Java-Classes
import dbConfig.Settings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Home implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JLabel searchlbl;
    private JTextField searchBar;
    private JButton searchBtn;
    private AddPanel AddObj;
    private JPanel AddPanel;
    private ProductTable tb;
    public JTable tbl;
    private JScrollPane scroll;
    private JLabel addLbl;
    private JButton delBtn;

    public void getHomePage() throws SQLException {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Store Manager");
        searchlbl = new JLabel("Search Item");
        searchBar = new JTextField(80);
        searchBtn = new JButton("Search");
        AddObj = new AddPanel(frame);

        frame.setBounds(400, 200, 700, 400);
        frame.setTitle("Store Manager");
        panel.setBounds(0, 0, 800, 600);
        panel.setBackground(Color.PINK);
        frame.add(panel);

        title.setBounds(300, 0, 400, 50);
        title.setForeground(Color.RED);
        title.setFont(title.getFont().deriveFont(20f));
        panel.add(title);

        searchlbl.setBounds(18, 55, 100, 20);
        searchlbl.setFont(searchlbl.getFont().deriveFont(15f));
        searchBar.setBounds(110, 50, 150, 30);
        searchBtn.setBounds(275, 55, 80, 20);
        searchBtn.setBackground(Color.YELLOW);
        searchBar.setForeground(Color.BLACK);
        searchBtn.addActionListener(this);
        panel.add(searchBar);
        panel.add(searchlbl);
        panel.add(searchBtn);

        // Table
        tb = new ProductTable();
        tbl = tb.getTable();
        tbl.isCellEditable(1, 1);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(15, 90, 350, 240);
        panel.add(scroll);



        delBtn = new JButton("Delete");
        delBtn.setBackground(Color.RED);
        delBtn.setForeground(Color.BLACK);
        delBtn.setBounds(10, 335, 80, 20);
        delBtn.addActionListener(this);
        panel.add(delBtn);


        addLbl = new JLabel("Add or Update Products");
        addLbl.setFont(searchlbl.getFont().deriveFont(15f));
        addLbl.setBounds(450, 40, 200, 50);
        addLbl.setForeground(Color.darkGray);
        panel.add(addLbl);

        // Add Panel
        AddPanel = AddObj.getAddPanel();
        AddPanel.setBounds(400, 90, 270, 240);
        panel.add(AddPanel);

        // Frame settings
        ImageIcon img = new ImageIcon("src/SMS/icon.png");
        frame.setIconImage(img.getImage());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Settings settings = Settings.getObject();
        if (e.getSource() == searchBtn){

            System.out.println("Searching");
        }
        if (e.getSource() == delBtn){

            if(tbl.getSelectedRowCount() < 1){
                JOptionPane.showMessageDialog(new JFrame(), "No row's selected.");
            }
            else if(tbl.getSelectedRowCount() > 1){
                JOptionPane.showMessageDialog(new JFrame(), "Only one row can be selected at a time.");
            }
            else{
                Connection con = settings.connectDb();
                int pID = Integer.parseInt((String) tbl.getValueAt(tbl.getSelectedRow(), 0));
                DefaultTableModel dm = (DefaultTableModel) tbl.getModel();

                settings.deleteProduct(con, pID, frame);
            }
        }
    }
}

