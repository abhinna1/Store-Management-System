package SMS;
// Packages to import
import dbConfig.DbAcc;
import dbConfig.Settings;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductTable{

    JTable table;
    DefaultTableModel dm;
    String query;
    Connection con;
    String[] info = {};
    String[] data;
    Settings settings;
    Statement st;
    ResultSet rs;
    String pName;
    String cp;
    String sp;
    JScrollPane scroll;

    // Constructor
    public JScrollPane getTable() throws SQLException {
        settings = new Settings(DbAcc.url, DbAcc.username, DbAcc.password);
        Connection con = settings.connectDb();
        st = con.createStatement();
        query = "select * from tbl_product;";
        st = con.createStatement();
        table = new JTable();
        dm = (DefaultTableModel) table.getModel();

        dm.addColumn("Product Name");
        dm.addColumn("Cost Price");
        dm.addColumn("Selling Price");
        rs = st.executeQuery(query);
        while(rs.next()){
            //Display values
            pName = rs.getString("product_name");
            cp = Integer.toString(rs.getInt("cost_price"));
            sp = Integer.toString(rs.getInt("selling_price"));
            data = new String[]{pName, cp, sp};
            dm.addRow(data);
        }
        scroll = new JScrollPane(table);
        return scroll;
    }


    // Driver  method


}