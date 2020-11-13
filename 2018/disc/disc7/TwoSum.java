import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {

    // Given an int x and a SORTED array A of N distinct integers,
    // design an algorithm to find if there exists indices i and j
    // such that A[i] + A[j] == x.
    public boolean findSum(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] + A[j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findSumFast(int[] A, int target) {
        //solution 1 : use hash set, whose contains method has a O(1) complexity
//        Set<Integer> s = new HashSet<>();
//        for(int a : A){
//            s.add(a);
//        }
//        for(int i = 0; i < A.length; i++){
//            if (s.contains(target - A[i]))
//                return true;
//        }
//        return false;
        //solution 2:
        int low = 0;
        int high = A.length - 1;
        for(; low <= high; ){
            if(A[low] + A[high] == target)
                return true;
            else if (A[low] + A[high] < target)
                low ++;
            else
                high ++;
        }
        return false;
    }

    public int binarySearch(int[] A, int x, int low, int high) {
        int middle = low + (high - low) / 2;
        if (low > high)
            return -1;
        if (A[middle] > x) {
            return binarySearch(A, x, low, middle - 1);
        }
        else if (A[middle] < x) {
            return binarySearch(A, x, middle + 1, high);
        }
        else {
            return middle;
        }
    }
}
