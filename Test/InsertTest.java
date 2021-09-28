
import SMS.ProductTable;
import dbConfig.Settings;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InsertTest {
    Boolean found;

    private JFrame getFrame() {
        JFrame frame = new JFrame();
        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private boolean TestMethod(String name, int CP, int SP) throws SQLException {
        Settings settings = Settings.getObject();
        Connection con = settings.connectDb();
        Statement st = con.createStatement();
        settings.insertProduct(con, name, CP, SP, this.getFrame());
        con = settings.connectDb();
        String query = "select count(*) from tbl_product where product_name = '" + name + "' and cost_price = " + CP + " and selling_price = " + SP +";";
        st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            found = true;
        } else {
            found = false;
        }

        return found;
    }

    @Test

    public void insertTest() throws SQLException {
        // Note: Test Case Parameters are requested to be names than existing product names.
        assertEquals(true, this.TestMethod("test1", 100, 200));

    }
}
