import org.w3c.dom.Node;

import java.util.Map;
import java.util.logging.ConsoleHandler;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    public static void successorDelete(BST bst){
        bst.deleteTakingSuccessor(bst.getRandomKey());
    }

    public static void randomDelete(BST bst){
        bst.deleteTakingRandom(bst.getRandomKey());
    }


    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        double depth = optimalAverageDepth(N);
        return (int) (depth * N);
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        double k = Math.log(N + 1) / Math.log(2);
        if(Math.floor(k) == k){
            return integerAveDepth(N);
        }
        else{
            int k_floor = (int) Math.floor(k);
            int k_dif = N - (int) Math.pow(2, k_floor) + 1;
            double totalDepth = totalDepth(k_floor - 1) + k_floor * k_dif;
            return  totalDepth / N;

        }
        //return 0;
    }

    public static double integerAveDepth(int N){
        double k =  (Math.log(N + 1) / Math.log(2));
        double totaldepth = totalDepth((int)k - 1);
        return totaldepth / N;
    }

    private static double totalDepth(int k){
        if (k == 0)
            return 0;
        else{
            return (k * Math.pow(2, k))  + totalDepth (k - 1);
        }
    }

    public static void main(String[] args){
        System.out.println(optimalAverageDepth(8));
        System.out.println(optimalIPL(1));
    }
}
