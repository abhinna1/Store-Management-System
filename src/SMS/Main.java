package SMS;

import java.sql.SQLException;

public class Main {
    static Home homePage = new Home();
    public static void main(String[] args) throws SQLException {
        homePage.getHomePage();


        // Get Date
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));

    }
}
