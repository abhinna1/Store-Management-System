import dbConfig.Settings;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTest {
    private boolean test(String name, int cp, int sp, int id) {
        boolean updated;
        try {
            Settings settings = Settings.getObject();
            settings.updateProduct(name, cp, sp, id);
            updated = true;
        }
        catch(Exception e){
            System.out.println(e);
            updated = false;
        }
        return updated;
    }
    @Test
    public void updateTest(){
        assertEquals(true, this.test("apple", 12, 14, 20));
    }

}
