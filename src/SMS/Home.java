package SMS;

// Java-Classes


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Home implements ActionListener, MouseListener {
    public static JFrame frame;
    final private JPanel panel;
    final private JLabel title;
    final private JLabel searchlbl;
    final private JTextField searchBar;
    final private JButton searchBtn;
    final private AddPanel AddObj;
    private JPanel AddPanel;
    final private ProductTable tb;
    public static JTable tbl;
    private JScrollPane scroll;
    private JLabel addLbl;
    private JButton delBtn;
    private JButton deselectBtn;

    public Home(){
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.title = new JLabel("Store Manager");
        this.searchlbl = new JLabel("Search Item");
        this.searchBar = new JTextField(80);
        this.searchBtn = new JButton("Search");
        this.AddObj = new AddPanel(frame, tbl);
        this.tb = new ProductTable();
        this.deselectBtn = new JButton("Delecect rows");
    }
    public void getHomePage() throws SQLException {

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
        tbl = tb.getTable();
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.isCellEditable(1, 1);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(15, 90, 350, 240);
        panel.add(scroll);
        tbl.addMouseListener(this);


        delBtn = new JButton("Delete");
        delBtn.setBackground(Color.RED);
        delBtn.setForeground(Color.BLACK);
        delBtn.setBounds(10, 335, 80, 20);
        delBtn.addActionListener(this);
        panel.add(delBtn);

        deselectBtn = new JButton("Deselect");
        deselectBtn.setBackground(Color.gray);
        deselectBtn.setForeground(Color.BLACK);
        deselectBtn.setBounds(100, 335, 100, 20);
        deselectBtn.addActionListener(this);
        panel.add(deselectBtn);


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
        ImageIcon img = new ImageIcon("src/SMS/Images/icon.png");
        frame.setIconImage(img.getImage());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchBtn){
            tb.search(tbl, searchBar.getText());
            System.out.println("Searching");
        }
        if (e.getSource() == delBtn){
            try {
                tb.deleteRow(tbl, frame);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == deselectBtn){
            tbl.clearSelection();
            AddObj.getNameField().setText("");
            AddObj.getCpField().setText("");
            AddObj.getspField().setText("");
            AddObj.getaddBtn().setEnabled(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (tbl.getSelectedRowCount() == 1){
            AddObj.getaddBtn().setEnabled(false);
            tb.getsetData(tbl, AddObj.getNameField(), AddObj.getCpField(), AddObj.getspField());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

