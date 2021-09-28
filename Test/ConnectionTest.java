import dbConfig.Settings;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ConnectionTest {
    @Test
    public void testCon() throws SQLException {
        Settings settings = Settings.getObject();
        Connection con=settings.connectDb();
        assertNotEquals(null, con);
        con.close();
    }
}
