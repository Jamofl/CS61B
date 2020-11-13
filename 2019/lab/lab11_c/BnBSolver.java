import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    public Pair<List<Bear>, List<Bed>> answer;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        this.answer = quickSort(bears, beds);
    }

    private <Item extends Comparable, Z extends Comparable> void partition(Z pivot, List<Item> list, List<Item> smaller, List<Item> equal, List<Item> bigger){
        for(int i = 0; i < list.size() ; i ++){
            Item temp = list.get(i);
            if(temp.compareTo(pivot) < 0)
                smaller.add(temp);
            else if(temp.compareTo(pivot) > 0)
                bigger.add(temp);
            else
                equal.add(temp);
        }
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return this.answer.first();
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return this.answer.second();
    }

    public <Item> List catenate(List<Item> list1, List<Item> list2){
        List<Item> toReturn = new LinkedList<>();
        for(Item i : list1)
            toReturn.add(i);
        for(Item i : list2)
            toReturn.add(i);
        return toReturn;
    }


    // quick sort bears and beds at the same time
    // always choose the first element of bears as the pivot of beds, then choose the bed with same size as the pivot
    // of bears; recursively do the quick sort process on smaller parts and bigger parts
    public Pair quickSort(List<Bear> bears, List<Bed> beds){
        if(bears.size() <= 1 || beds.size() <= 1)
            return new Pair<>(bears, beds);

        List<Bear> smallerBear = new LinkedList<>();
        List<Bear> equalBear = new LinkedList<>();
        List<Bear> biggerBear = new LinkedList<>();

        List<Bed> smallerBed = new LinkedList<>();
        List<Bed> equalBed = new LinkedList<>();
        List<Bed> biggerBed = new LinkedList<>();

        Bed pivotBed = beds.get(0);
        int tempSize = pivotBed.getSize(); // always get the first bed in beds as the pivot for bears
        Bear pivotBear = new Bear(tempSize); // use the bear with same size as the pivot for beds

        partition(pivotBed,  bears, smallerBear, equalBear, biggerBear);
//        Bear pivotBear = equalBear.get(0);
        partition(pivotBear, beds,  smallerBed,  equalBed,  biggerBed);

        Pair<List<Bear>, List<Bed>> smallerPair = quickSort(smallerBear, smallerBed);
        Pair<List<Bear>, List<Bed>> biggerPair = quickSort(biggerBear, biggerBed);

        smallerBear = smallerPair.first();
        smallerBed = smallerPair.second();
        biggerBear = biggerPair.first();
        biggerBed = biggerPair.second();
        List<Bear> sortedBears = catenate(catenate(smallerBear, equalBear), biggerBear);
        List<Bed> sortedBeds = catenate(catenate(smallerBed, equalBed), biggerBed);
        Pair<List<Bear>, List<Bed>> pair = new Pair(sortedBears, sortedBeds);
        return pair;
    }

    public static void main(String[] args){
        int[] bearSizes = {10, 50, 40};
        int[] bedSizes = {50, 10, 40};
        ArrayList<Bear> bears = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        for (int i = 0; i < bearSizes.length; i++) {
            bears.add(new Bear(bearSizes[i]));
            beds.add(new Bed(bedSizes[i]));
        }
        BnBSolver solver = new BnBSolver(bears, beds);
        List l1 = solver.solvedBears();
        List l2 = solver.solvedBeds();
    }
}
