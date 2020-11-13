import org.junit.rules.DisableOnDebug;

import javax.naming.InsufficientResourcesException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flight in the Flight problem.
 */
public class Flight {

    int startTime;
    int endTime;
    int passengers;

    private List<ArrayList<Integer>> lst;
    private List<Integer>[] lst2;

    public Flight(int start, int end, int numPassengers) {
        startTime = start;
        endTime = end;
        passengers = numPassengers;
        this.lst = new ArrayList(5);
        this.lst2 =  (List<Integer>[]) new ArrayList[5];
        for(int i = 0; i < 5; i ++)
        {
            lst.add(new ArrayList<Integer>());
            lst2[i] = new ArrayList<Integer>();
        }
    }

    public int startTime() {
        return startTime;
    }

    public int endTime() {
        return endTime;
    }

    public int passengers() {
        return passengers;
    }

    public static void main(String[] args){
        Flight f = new Flight(1,5,4);
    }
}
