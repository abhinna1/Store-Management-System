package dbConfig;

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
            System.out.println("Connected");
        }
        catch(Exception e){
                System.out.println(e);
                System.out.println("error");
            }
        return con;
    }
    public void insertProduct(Connection con, String product_name, int cost_price, int selling_price){
        try {
            Statement stmt = con.createStatement();
            stmt.execute("insert into tbl_product(product_name, cost_price, selling_price) " +
                    "values(" + "'" + product_name + "'" + ',' + "'" + cost_price + "'" + ',' + "'" + selling_price + "'" + ')' + ';');

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
