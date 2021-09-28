
import dbConfig.Settings;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class DeleteTest {
    private JFrame getFrame() {
        JFrame frame = new JFrame();
        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private boolean delTest(int id){
        boolean deleted;
        Settings settings = Settings.getObject();
        Connection con = settings.connectDb();
        try {
            settings.deleteProduct(con, id, this.getFrame());
            deleted = true;
        }
        catch(Exception e){
            System.out.println(e);
            deleted = false;
        }
        return deleted;
    }
    @Test
    public void deleteTest(){
        assertEquals(true,delTest(38));
    }

}
