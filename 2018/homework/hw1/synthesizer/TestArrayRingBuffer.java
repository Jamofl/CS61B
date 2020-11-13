package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer(6);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        buffer.dequeue();
        buffer.enqueue(4);
        buffer.dequeue();
        buffer.enqueue(5);
        buffer.enqueue(6);
        buffer.enqueue(7);
        //buffer.enqueue(9);
        int temp = (int)buffer.peek();

        for( Integer b : buffer){
            System.out.println(b);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
