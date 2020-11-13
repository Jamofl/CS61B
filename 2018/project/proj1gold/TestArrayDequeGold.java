import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {

    public int count = 10000;
    public double[] randoms = new double[count];
    public void random(){
        for(int i = 0; i< count; i++){
            double numberBetweenZeroAndOne = StdRandom.uniform();
            randoms[i] = numberBetweenZeroAndOne;
        }
    }

    @Test
    //@source
    public void testRandomly(){
        //StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> good1 = new ArrayDequeSolution<>();
//        Deque<Integer> sad1 = new LinkedListDeque<>();
        Deque<Integer> sad1 = new ArrayDeque<>();
//        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        String[] orders = new String[count];
        String message = "\n";

        random();

        for (int i = 0; i < count; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            //System.out.println(numberBetweenZeroAndOne);

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                good1.addLast(i);
                message += "addLast(" + i + ")" + "\n";
            }
            else if (numberBetweenZeroAndOne < 0.5){
                sad1.addFirst(i);
                good1.addFirst(i);
                message += "addFirst(" + i + ")" + "\n";

            }
            else if (numberBetweenZeroAndOne < 0.75){
                if(good1.size() > 1) {
                    message += "removeFirst()" + "\n";
                    assertEquals(message, good1.removeFirst(), sad1.removeFirst());
                }
            }
            else{
                if(good1.size() > 1){
                    message += "removeLast()" + "\n";
                    assertEquals(message, good1.removeLast(), sad1.removeLast());
                }
            }
        }
    }
}
