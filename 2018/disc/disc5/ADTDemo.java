import java.util.HashSet;
import java.util.Set;

public class ADTDemo {
    /*
    Given an array of integers A and an integer k, return true if any two numbers
    in the array sum up to k, and return false otherwise. How would you do this?
    Give the main idea and what ADT you would use.
     */

    public boolean sumOfTwo(int[] l, int k){
        int target;
        Set<Integer> haveSeen = new HashSet<>();
        for( int i : l){
            target = k - i;
            if ( haveSeen.contains(target))
                return true;
            else{
                haveSeen.add(i);
            }
        }
        return false;
    }
}
