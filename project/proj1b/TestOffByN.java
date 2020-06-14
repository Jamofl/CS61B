import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {


    static CharacterComparator OffByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertTrue(OffByN.equalChars('a','f'));
        assertFalse(OffByN.equalChars('a','b'));
        assertTrue(OffByN.equalChars('b','g'));
    }
}
