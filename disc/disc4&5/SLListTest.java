import org.junit.Test;
import static org.junit.Assert.*;
public class SLListTest {
    @Test
    public void insertTest(){
        SLList l = new SLList(5);
        l.addFirst(3);
        l.addFirst(1);
        l.insert(2,6);
    }

    @Test
    public void reverseTest() {
        SLList l = new SLList(5);
        l.addFirst(3);
        l.addFirst(1);
        l.reverse();
    }


}
