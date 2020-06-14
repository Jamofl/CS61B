import static org.junit.Assert.*;
import org.junit.Test;
public class FlikTest {
    @Test
    public void Fliktest(){
        boolean result1 = Flik.isSameNumber(1,2);
        boolean result2 = Flik.isSameNumber(1,1);

        assertFalse(result1);
        assertTrue(result2);


    }
}
