import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterator <Integer> {
    public int k;
    private ArrayList curList;
    private boolean hasNext;
    public KthIntList(ArrayList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    @Override
    public boolean hasNext(){
        return this.hasNext;
    }

    public Integer next(){
        if (this.curList == null)
            throw new NoSuchElementException();
        else {
            int toReturn = (int) this.curList.get(0);
            this.curList = (ArrayList) this.curList.subList(k, -1);
            hasNext = (curList != null);
            return toReturn;
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> lst = new ArrayList();
        lst.add(0);
        lst.add(1);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(5);
        lst.add(6);
        lst.add(7);
        lst.add(8);
        lst.add(9);
        for(Iterator<Integer> p = new KthIntList(lst, 2); p.hasNext();){
            System.out.println(p.next());
        }


    }
}


