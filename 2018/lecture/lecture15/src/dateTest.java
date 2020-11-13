import org.junit.Test;
import static org.junit.Assert.*;

public class dateTest {

    @Test
    public void testEquals(){
        Date d1 = new Date(20,5,2020);
        Date d2 = new Date(20,5,2020);
        Date d3 = new Date(30,5,2020);
        assertEquals(d1, d2);
        assertNotEquals(d1, d3);
        assertNotEquals(d1, "horse");
        assertNotEquals(d1, null);

    }

}

