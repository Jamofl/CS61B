import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    public ArrayList<Flight> flightList;
    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        this.flightList = flights;
    }

    public int solve() {
        /* FIX ME */

        Comparator<Flight> com1 = (Flight f1, Flight f2) -> f1.startTime - f2.startTime;
        flightList.sort(com1); //根据startTime进行排序

        Comparator<Integer> comparator = (Integer i, Integer j) -> j - i;
        PriorityQueue<Integer> q = new PriorityQueue(comparator);//大顶堆
        for(int i = 0 ; i < flightList.size(); i++){
            int total = flightList.get(i).passengers;
            for(int j = i + 1; j < flightList.size(); j ++){
                if (flightList.get(j).startTime <= flightList.get(i).endTime &&
                        flightList.get(j).endTime >= flightList.get(i).endTime)
                    total += flightList.get(j).passengers;
                if (flightList.get(j).startTime > flightList.get(i).endTime)
                    break;

            }
            q.add(total);
        }
        return q.poll();
    }

}
