import java.util.*;

public class Intersect {

    // Intersect Now do the same as above, but find the intersection between
    // both ar- rays. The intersection of two arrays is the list of all elements
    // that are in both arrays. Again assume that neither array has duplicates.
    // For example, the intersection of {1, 2, 3, 4}and{3, 4, 5, 6}is{3, 4}.
    // Hint: Think about using ADTs other than arrays to make the code more efficient.

    public static int[] intersect(int[] A, int[] B){
        Set<Integer> intersection = new HashSet<>();
        Set<Integer> setA = new HashSet<>();
        for(int a:A)
            setA.add(a);
        for(int b:B){
            if(setA.contains(b))
                intersection.add(b);
        }

//        Map<Integer, Integer> map = new HashMap<>();
//        for(int a : A){
//            map.put(a, 1);
//        }
//        for(int b : B){
//            if(map.containsKey(b)){
//                map.put(b, 2);
//            }
//            else{
//                map.put(b,1);
//            }
//        }
//        for(int key : map.keySet()){
//            if (map.get(key) == 2)
//                intersection.add(key);
//        }
        int[] toReturn = new int[intersection.size()];
        int index = 0;
        for(int num : intersection){
            toReturn[index] = num;
            index ++;
        }
        return toReturn;
    }
    public static void main(String[] args){
        int[] A = {1,2,3,4};
        int[] B = {3,4,5,6};
        int[] C = intersect(A, B);
    }
}
