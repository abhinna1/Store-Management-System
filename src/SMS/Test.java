package SMS;

import java.sql.*;
import java.util.Arrays;

public class Test {
    static Connection con;
    static String[] info = {};
    static String[][] data;
    public static void main(String[] args) {

        String query = "select * from tbl_product;";
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/storedb", "Abhinna", "$abhi123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                //Display values
                System.out.println("Product: " + rs.getString("product_name"));
                System.out.println("Cost Price: " + rs.getInt("cost_price"));
                System.out.println("Sales Price: " + rs.getInt("selling_price"));
            }
        }
        catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
