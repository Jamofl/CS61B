import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestSortAlgs {


    static int count = 100000;

    @Test
    public void testQuickSort() {
        Queue<String> q = new Queue<>();
        for(int i = count; i > 0; i --){
            q.enqueue("a" + i);
        }
        Queue<String> res =  QuickSort.quickSort(q);
        assertTrue(isSorted(res));
    }

    @Test
    public void testQuickSort2() {
        Queue<String> q = new Queue<>();
        for(int i = 0; i < count; i ++){
            q.enqueue("a" + i);
        }
        Queue<String> res =  QuickSort.quickSort(q);
        assertTrue(isSorted(res));
    }

    @Test
    public void testQuickSort3() {
        Queue<String> q = new Queue<>();
        for(int i = 10000; i > 0; i --){
            q.enqueue("a");
        }
        Queue<String> res =  QuickSort.quickSort(q);
        assertTrue(isSorted(res));
    }

    @Test
    public void testQuickSort4() {
        Queue<String> q = new Queue<>();
        q.enqueue("a");
        q.enqueue("a");
        q.enqueue("a");
        q.enqueue("a");
        q.enqueue("a");
        q.enqueue("a");
        Queue<String> res =  QuickSort.quickSort(q);
        assertTrue(isSorted(res));
    }

    @Test
    public void testMergeSort() {
        Queue<String> q = new Queue<>();
        q.enqueue("b");
        q.enqueue("d");
        q.enqueue("a");
        q.enqueue("f");
        q.enqueue("e");
        q.enqueue("c");
        Queue<String> res =  MergeSort.mergeSort(q);
        assertTrue(isSorted(res));
    }

    @Test
    public void testMergeSort2() {
        Queue<String> q = new Queue<>();
        for(int i = 0; i < count; i ++){
            q.enqueue("a" + i);
        }
        Queue<String> res =  MergeSort.mergeSort(q);
        assertTrue(isSorted(res));
    }

    @Test
    public void testMergeSort3() {
        Queue<String> q = new Queue<>();
        for(int i = count; i > 0; i --){
            q.enqueue("a" + i);
        }
        Queue<String> res =  MergeSort.mergeSort(q);
        assertTrue(isSorted(res));
    }


    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
