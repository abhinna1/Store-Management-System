package SMS;

import dbConfig.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;


public class AddPanel implements ActionListener {
    private JPanel panel;
    private JLabel nameLbl;
    private JTextField nameEnt;
    private JLabel cpLbl;
    private JTextField cpEnt;
    private JLabel spLbl;
    private JTextField spEnt;
    private JButton addBtn;
    private JButton updateBtn;
    private Settings settings;
    private JFrame frame;
    private JTable tbl;

    ProductTable productTable;
    public AddPanel(JFrame frame, JTable tbl){
        this.frame = frame;
        this.tbl = tbl;
    }
    public JPanel getAddPanel(){
        panel = new JPanel();
        nameLbl = new JLabel("Product Name");
        nameEnt = new JTextField(30);
        cpLbl = new JLabel("Cost Price");
        cpEnt = new JTextField(30);
        spLbl = new JLabel("Selling Price");
        spEnt = new JTextField(30);
        addBtn = new JButton("Add Product");
        updateBtn = new JButton("Update");
        productTable = new ProductTable();

        nameLbl.setBounds(10, 30, 100, 20);
        nameEnt.setBounds(110, 22, 140, 40);
        panel.add(nameLbl);
        panel.add(nameEnt);

        cpLbl.setBounds(10, 90, 100, 20);
        cpEnt.setBounds(110, 82, 140, 40);
        panel.add(cpLbl);
        panel.add(cpEnt);

        spLbl.setBounds(10, 150, 100, 20);
        spEnt.setBounds(110, 142, 140, 40);
        panel.add(spLbl);
        panel.add(spEnt);

        addBtn.setBounds(10, 202, 110, 30);
        addBtn.setBackground(Color.GREEN);
        addBtn.addActionListener(this);
        panel.add(addBtn);

        updateBtn.setBounds(150, 202, 110, 30);
        updateBtn.setBackground(Color.BLUE);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.addActionListener(this);
        panel.add(updateBtn);


        panel.setBounds(0, 0, 300, 300);
        panel.setLayout(null);


        return panel;
    }

    public JTextField getNameField(){
        return nameEnt;
    }
    public JTextField getCpField(){
        return cpEnt;
    }
    public JTextField getspField(){
        return spEnt;
    }
    public JButton getaddBtn(){
        return addBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            if((nameEnt.getText().length()<1)||(cpEnt.getText().length()<1)||(spEnt.getText().length()<1)){
                JOptionPane.showMessageDialog(new JFrame(), "Fill all the credentials.", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    settings = Settings.getObject();
                    Connection con = settings.connectDb();
                    System.out.println("Connected");
                    int cpInt = Integer.parseInt(cpEnt.getText());
                    int spInt = Integer.parseInt(spEnt.getText());
                    settings.insertProduct(con, nameEnt.getText(), cpInt, spInt, frame);
                    System.out.println("Added");
                    con.close();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Data Entered", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == updateBtn){
            try {
                if(Home.tbl.getSelectedRowCount()<1){
                    JOptionPane.showMessageDialog(new JFrame(), "No rows selected.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else if(Home.tbl.getSelectedRowCount()>1){
                    JOptionPane.showMessageDialog(new JFrame(), "Only one row can be updated at a time.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    productTable.updateTbl(Home.frame, Home.tbl, nameEnt, cpEnt, spEnt);
                    System.out.println("Updated");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error while updating");
            }
        }

    }
}
