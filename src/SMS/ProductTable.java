package SMS;
// Packages to import
import dbConfig.Settings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductTable{

    private JTable table;
    private DefaultTableModel dm;
    private String query;
    private Connection con;
    private String[] data;
    private static Settings settings;
    private Statement st;
    private ResultSet rs;
    private String pName;
    private String cp;
    private String sp;
    private String profit;
    private JScrollPane scroll;
    private String PID;

    // Get data from database
    public JTable getTable() throws SQLException {
        settings = Settings.getObject();
        con = settings.connectDb();
        st = con.createStatement();
        query = "select * from tbl_product;";
        st = con.createStatement();
        table = new JTable(new DefaultTableModel());
        dm = (DefaultTableModel) table.getModel();


        dm.addColumn("Product ID");
        dm.addColumn("Product Name");
        dm.addColumn("Cost Price");
        dm.addColumn("Selling Price");
        dm.addColumn("Profit");


        rs = st.executeQuery(query);
        while(rs.next()){
            //Display values
            table.repaint();
            PID = rs.getString("product_id");
            pName = rs.getString("product_name");
            cp = Integer.toString(rs.getInt("cost_price"));
            sp = Integer.toString(rs.getInt("selling_price"));
            profit = Integer.toString(rs.getInt("selling_price") - rs.getInt("cost_price"));

            data = new String[]{PID, pName, cp, sp, profit};
            dm.addRow(data);
        }
        con.close();
        return table;
    }

    // Delete Data
    public void deleteRow(JTable tbl, JFrame frame) throws SQLException {
        if(tbl.getSelectedRowCount() < 1){
            JOptionPane.showMessageDialog(new JFrame(), "No row's selected.");
        }
        else if(tbl.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(new JFrame(), "Only one row can be selected at a time.");
        }
        else{
            Connection con = settings.connectDb();
            System.out.println("Connected");
            int pID = Integer.parseInt((String) tbl.getValueAt(tbl.getSelectedRow(), 0));
            settings.deleteProduct(con, pID, frame);
            con.close();

        }
    }

    // Search Filter
    public void search(JTable tbl, String text) {
        DefaultTableModel table = (DefaultTableModel) tbl.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tbl.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(text));
    }
    public static void getsetData(JTable tbl, JTextField nameEnt, JTextField cpEnt, JTextField spEnt){
        // Set TextField values to table data
        int selectedRow = tbl.getSelectedRow();

        String name = (String) tbl.getValueAt(selectedRow, 1);
        String cp = (String) tbl.getValueAt(selectedRow, 2);
        String sp = (String) tbl.getValueAt(selectedRow, 3);
        nameEnt.setText(name);
        cpEnt.setText(String.valueOf(Integer.parseInt(String.valueOf(cp))));
        spEnt.setText(String.valueOf(Integer.parseInt(String.valueOf(sp))));
    }


    public static void updateTbl(JFrame frame, JTable tbl, JTextField nameEnt, JTextField cpEnt, JTextField spEnt) throws SQLException {
        String name = nameEnt.getText();
        int cp = Integer.parseInt(cpEnt.getText());
        int sp = Integer.parseInt(spEnt.getText());
        int id = Integer.parseInt((String) tbl.getValueAt(tbl.getSelectedRow(), 0));
        String statement = "update tbl_product " +
                " set product_name = " + "'" + name + "',"+"cost_price = " + cp + ',' + " selling_price = "+sp+
                " where product_id = "+ id;
        System.out.println(statement);
        settings = Settings.getObject();
        Connection con = settings.connectDb();
        System.out.println("Connected");
        Statement stat = con.createStatement();
        stat.execute(statement);
        Settings.refreshPage(frame);
        con.close();
        System.out.println("Connection closed");


    }



}