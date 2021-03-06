package dbConfig;

import SMS.Home;
import SMS.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Settings {
    private String dbLink;
    private String username;
    private String password;
    private Connection con;

    public Settings(String dbLink, String username, String password){
        this.dbLink = dbLink;
        this.username = username;
        this.password = password;
    }

    public Connection connectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(dbLink, username, password);
        }
        catch(Exception e){
                System.out.println(e);
                System.out.println("error");
            }
        return con;
    }
    public void insertProduct(Connection con, String product_name, int cost_price, int selling_price, JFrame frame){
        try {
            Statement stmt = con.createStatement();
            stmt.execute("insert into tbl_product(product_name, cost_price, selling_price) " +
                    "values(" + "'" + product_name + "'" + ',' + "'" + cost_price + "'" + ',' + "'" + selling_price + "'" + ')' + ';');
            refreshPage(frame);
            con.close();
            System.out.println("Connection closed");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteProduct(Connection con, int pId, JFrame frame){
        try {
            Statement stmt = con.createStatement();
            stmt.execute("delete from tbl_product where product_id = " + pId +';');
            refreshPage(frame);
            con.close();
            System.out.println("Connection closed");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateProduct(String name, int cp, int sp, int id) throws SQLException {
        String statement = "update tbl_product " +
                " set product_name = " + "'" + name + "',"+"cost_price = " + cp + ',' + " selling_price = "+sp+
                " where product_id = "+ id;
        Settings settings = Settings.getObject();
        Connection con = settings.connectDb();
        Statement stat = con.createStatement();
        stat.execute(statement);
        con.close();
    }

    public static void refreshPage(JFrame frame) throws SQLException {
        frame.dispose();
        new Home().getHomePage();
    }


    public static Settings getObject(){
        Settings settings = new Settings(DbAcc.url, DbAcc.username, DbAcc.password);
        return settings;
    }

}
