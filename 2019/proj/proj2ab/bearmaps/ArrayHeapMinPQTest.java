package bearmaps;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void addRemoveTest(){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naiveqp = new NaiveMinPQ<>();
        int num = 3;
        for(int i = num ; i > 0; i--){
            pq.add("a" + i, (double) i);
            naiveqp.add("a" +  i, (double) i);
            assertEquals(pq.getSmallest(), naiveqp.getSmallest());

        }
        for(int i = num ; i > 0; i--) {
            String a = pq.removeSmallest();
            String b = naiveqp.removeSmallest();
            assertEquals(a, b);
        }
    }

    @Test
    public void changePriorityTest(){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 10;
        for(int i = num; i >= 0; i--){
            pq.add("a" + i, (double) i);
//            naivepq.add("a" +  i, (double) i);
//            assertEquals(pq.getSmallest(), naivepq.getSmallest());
        }
        for(int i = 0; i <= num; i++){
            pq.changePriority("a" + i, (double) num - i);
            //naivepq.changePriority("a" +  i, (double) num - i);
        }
//        for(int i = 0; i <= num; i++){
//            assertEquals(pq.removeSmallest(), naivepq.removeSmallest());
//        }

    }

    @Test
    public void containsSizeTest(){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 1000;
        for(int i = 0; i < num; i++){
            pq.add("a" + i, (double) i);
            //naivepq.add("a" +  i, (double) i);
            //assertEquals(pq.size(),naivepq.size());
            assertEquals(pq.size(),i + 1);

        }
        for(int i = 0; i < num; i++){
            assertTrue(pq.contains("a" + i));
            pq.removeSmallest();
            assertFalse(pq.contains("a" + i));
        }
    }

    @Test
    public void testAdd12345(){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        int num = 5;
        for(int i = 1; i <= num; i++) {
            pq.add(String.valueOf(i), i);
        }
        Object[] list = pq.getList();
        String[] exp = {null, "1", "2", "3", "4", "5"};
        assertEquals(list, exp);
    }

    @Test
    public void initialTest(){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        assertEquals(pq.size(), 0);
        assertFalse(pq.contains("a"));
    }

    @Test
    public void addTimeTest(){
        System.out.println("Add Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 10000;
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < num; i++){
            pq.add("a" + i, (double) i);
        }
        System.out.println("Total time elapsed for my pq: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < num; i++){
            naivepq.add("a" + i, (double) i);
            naivepq.contains("a" + i);
        }
        System.out.println("Total time elapsed for naive pq: " + sw2.elapsedTime() +  " seconds.");
    }

    @Test
    public void containsTimeTest(){
        System.out.println("Contains Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 10000;
        for(int i = 0; i < num; i++){
            pq.add("a" + i, (double) i);
            naivepq.add("a" + i, (double) i);
        }
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < num; i++) {
            pq.contains("a" + i);
        }
        System.out.println("Total time elapsed for my pq: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < num; i++){
            naivepq.contains("a" + i);
        }
        System.out.println("Total time elapsed for naive pq: " + sw2.elapsedTime() +  " seconds.");
    }

    @Test
    public void getSmallestTimeTest(){
        System.out.println("GetSmallest Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 10000;
        for(int i = 0; i < num; i++){
            pq.add("a" + i, (double) i);
            naivepq.add("a" + i, (double) i);
        }
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < num; i++) {
            pq.getSmallest();
        }
        System.out.println("Total time elapsed for my pq: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < num; i++){
            naivepq.getSmallest();
        }
        System.out.println("Total time elapsed for naive pq: " + sw2.elapsedTime() +  " seconds.");

    }

    @Test
    public void removeSmallestTimeTest(){
        System.out.println("RemoveSmallest Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 10000;
        for(int i = 0; i < num; i++){
            pq.add("a" + i, (double) i);
            naivepq.add("a" + i, (double) i);
        }
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < num; i++) {
            pq.removeSmallest();
        }
        System.out.println("Total time elapsed for my pq: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < num; i++){
            naivepq.removeSmallest();
        }
        System.out.println("Total time elapsed for naive pq: " + sw2.elapsedTime() +  " seconds.");
    }

    @Test
    public void SizeTimeTest(){
        System.out.println("Size Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 1000000;
        for(int i = 0; i < num; i++){
            pq.add("a" + i, (double) i);
            naivepq.add("a" + i, (double) i);
        }
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < num; i++) {
            pq.size();
        }
        System.out.println("Total time elapsed for my pq: " + sw1.elapsedTime() +  " seconds.");
        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < num; i++){
            naivepq.size();
        }
        System.out.println("Total time elapsed for naive pq: " + sw2.elapsedTime() +  " seconds.");
    }

    @Test
    public void changePriority(){
        System.out.println("ChangePriority Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> naivepq = new NaiveMinPQ<>();
        int num = 1000000;
        for(int i = 0; i <= num; i++){
            pq.add("a" + i, (double) i);
            naivepq.add("a" + i, (double) i);
        }
        Stopwatch sw1 = new Stopwatch();
        for(int i = num; i >= num; i--) {
            pq.changePriority("a" + i, num - i);
        }
        System.out.println("Total time elapsed for my pq: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i = num; i >= num; i--) {
            naivepq.changePriority("a" + i, num - i);
        }
        System.out.println("Total time elapsed for naive pq: " + sw2.elapsedTime() +  " seconds.");
    }

    @Test
    public void totalTimeTest(){
        System.out.println("all methods Time Test");
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        int num = 1000000;
        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i <= num; i++){
            pq.add("a" + i, (double) i);
        }
        System.out.println("Total time elapsed for my pq's add: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i <= num; i++){
            pq.contains("a" + i);
        }
        System.out.println("Total time elapsed for my pq's contains: " + sw2.elapsedTime() +  " seconds.");

        Stopwatch sw3 = new Stopwatch();
        for(int i = 0; i <= num; i++){
            pq.getSmallest();
        }
        System.out.println("Total time elapsed for my pq's getSmallest: " + sw3.elapsedTime() +  " seconds.");

        Stopwatch sw4 = new Stopwatch();
        for(int i = 0; i <= num; i++){
            pq.removeSmallest();
        }
        System.out.println("Total time elapsed for my pq's removeSmallest: " + sw4.elapsedTime() +  " seconds.");

        Stopwatch sw5 = new Stopwatch();
        for(int i = 0; i <= num; i++){
            pq.add("a" + i, (double) i);
            pq.size();
        }
        System.out.println("Total time elapsed for my pq's size: " + sw5.elapsedTime() +  " seconds.");

        Stopwatch sw6 = new Stopwatch();
        for(int i = 0; i <= num; i++){
            pq.changePriority("a" + i, num - i);
        }
        System.out.println("Total time elapsed for my pq's changePriority: " + sw6.elapsedTime() +  " seconds.");

    }
}
