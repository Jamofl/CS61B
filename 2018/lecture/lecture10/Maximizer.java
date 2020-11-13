public class Maximizer {

    // max function does not need to change with concrete class, as long as it is inherited from Ourcomparable
    public static Comparable max(Comparable[] items){
        int maxIndex = 0;
        for(int i = 1; i < items.length; i++){
            if (items[maxIndex].compareTo(items[i]) < 0){
                maxIndex = i;
            }
        }
        return items[maxIndex];
    }
}
