import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Union {

    // Union
    // Write the code that returns an array that is the union
    // between two given arrays. The union of two arrays is a list
    // that includes everything that is in both arrays, with no duplicates.
    // Assume the given arrays do not contain duplicates.
    // For example,theunionof{1, 2, 3, 4}and{3, 4, 5, 6}is{1, 2, 3, 4, 5, 6}.
    // Hint: The method should run in O(M + N) time where M and N is the size
    // of each array.

    public static int[] unionArray(int[] A, int[] B){
        int indexA = 0;
        int indexB = 0;
        int index = 0;
        int[] newArray = new int[A.length + B.length];
        while(true){
            if(indexA == A.length - 1){
                System.arraycopy(B, indexB, newArray, index, B.length - indexB);
                return newArray;
            }
            if(indexB == B.length - 1){
                System.arraycopy(A, indexA, newArray, index, A.length - indexA);
                return newArray;
            }

            if(A[indexA] < B[indexB]){
                newArray[index] = A[indexA];
                indexA ++;
                index ++;
            }
            else if(A[indexA] > B[indexB]){
                newArray[index] = B[indexB];
                indexB ++;
                index ++;
            }
            else{
                newArray[index] = B[indexB];
                indexA ++;
                indexB ++;
                index ++;
            }
        }
    }

    // official solution : use set!!!(remove the duplicate element automatically)
    public static int[] unionArrayFast(int[] A, int[] B){
        Set<Integer> s = new HashSet<>();
        for(int a : A)
            s.add(a);
        for(int b : B)
            s.add(b);
        int[] toReturn = new int[s.size()];
        Iterator<Integer> iter = s.iterator();
        for(int i = 0; i < toReturn.length; i ++){
            toReturn[i] = iter.next();
        }
//        int index = 0;
//        for(int num : s){
//            toReturn[index] = num;
//            index ++;
//        }
        return toReturn;

    }
    public static void main(String[] args){
        int[] A = {1,2,3,4};
        int[] B = {3,4,5,6};
        int[] C = unionArrayFast(A, B);
    }
}
