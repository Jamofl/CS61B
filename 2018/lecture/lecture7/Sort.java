import javax.swing.text.html.HTMLEditorKit;

public class Sort {
    public static void sort(String[] input){
        // find the smallest one
        // move it to the front
        // selection sort the rest
        sort(input, 0);

    }
    // 3 selection sort the rest
    private static void sort(String[] input, int start){
        if (start == input.length)
            return;
        int smallestIndex = findSmallest(input, start);
        swap(input, start, smallestIndex);
        sort(input, start + 1);
    }

    // 1 find the smallest one
    /** return the smallest string in inputs, starting at start*/
    public static int findSmallest(String[] input, int start){
        int minIndex = start;
        for(int i = start; i < input.length; i++){
            if (input[i].compareTo(input[minIndex]) < 0)
                minIndex = i;
        }
        return minIndex;
    }

    // 2 move it to the front
    /** swap two items with index a and index b in an array */
    public static void swap(String[] input, int a, int b){
        String temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }


    public static void selectingSort(int[] input){
        // find the smallest one
        // move it to the front
        // selection sort the rest
        int min_index = 0;
        for(int i = 0; i < input.length; i++){
            min_index = i;
            for(int j = i; j < input.length; j ++){
                if (input[j] < input[min_index]){
                    min_index = j;
                }
            }
            int temp = input[i];
            input[i] = input[min_index];
            input[min_index] = temp;
        }
    }

    public static void main(String[] args)
    {
        int[] t = {4,6,2,7,8,1};
        selectingSort(t);
    }
}
