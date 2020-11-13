import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayTest {
    @Test
    public void insertTest(){
        int[] arr = {1,3,5,7};
        int[] expect = {1,3,5,7,4};

        int[] result = Arr.insert(arr, 4, 10);
        assertArrayEquals(expect, result);
    }

    @Test
    public void  replicateTest(){
        int[] arr = {4,2,1};
        int[] exp = {4,4,4,4,2,2,1};
        int[] result = Arr.replicate(arr);
        assertArrayEquals(result, exp);
    }

    @Test
    public void flattenTest(){
        int[][] arr = new int[][]{{1},{1,2,3},{4,5,1}};
        int[] exp = {1,1,2,3,4,5,1};
        int[] result = Arr.flatten(arr);
        assertArrayEquals(exp, result);

    }
}
