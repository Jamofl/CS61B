import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort{
    /** Test the Sort.sort method*/
    @Test
    public  void testSort(){
        String[] input = new String[]{"i", "have", "some", "water"};
        String[] expect = new String[]{"have", "i", "some", "water"};
        Sort.sort(input);
        assertArrayEquals(expect, input);
    }

    @Test
    public  void testfindsmllest(){
        String[] input = new String[]{"i", "have", "some", "water"};
        int expect = 1;
        int output = Sort.findSmallest(input, 0);
        assertEquals(expect, output);
    }



}