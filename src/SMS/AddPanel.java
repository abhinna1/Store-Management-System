package SMS;

import dbConfig.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


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

    public AddPanel(JFrame frame){
        this.frame = frame;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            if((nameEnt.getText().length()<1)||(cpEnt.getText().length()<1)||(spEnt.getText().length()<1)){
                JOptionPane.showMessageDialog(new JFrame(), "Enter valid data", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                settings = Settings.getObject();
                Connection con = settings.connectDb();
                int cpInt = Integer.parseInt(cpEnt.getText());
                int spInt = Integer.parseInt(spEnt.getText());
                settings.insertProduct(con, nameEnt.getText(), cpInt, spInt, frame);
                System.out.println("Added");
            }
        }
        if (e.getSource() == updateBtn){
            System.out.println("Updating");
        }
    }
}
