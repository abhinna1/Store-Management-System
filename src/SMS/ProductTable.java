package SMS;
// Packages to import
import dbConfig.Settings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private Settings settings;
    private Statement st;
    private ResultSet rs;
    private String pName;
    private String cp;
    private String sp;
    private String profit;
    private JScrollPane scroll;
    private String PID;

    // Constructor
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
            cp = "Rs. " + rs.getInt("cost_price");
            sp = "Rs. " + rs.getInt("selling_price");
            profit = "Rs. " + (rs.getInt("selling_price") - rs.getInt("cost_price"));

            data = new String[]{PID, pName, cp, sp, profit};
//            dm.fireTableDataChanged();
            dm.addRow(data);
        }

        con.close();
        return table;
    }
    public void search(JTable tbl, JTextField text) {
        tbl.getRowSorter();

    }

    public DefaultTableModel getModel(){
        return dm;
    }
}