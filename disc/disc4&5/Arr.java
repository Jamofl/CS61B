public class Arr {

    public static int[] insert(int[] arr, int item, int position){
        int size = arr.length;
        int[] temp = new int[size + 1];
        if(position >= size){
            System.arraycopy(arr,0, temp, 0, size);
            temp[size] = item;
            return temp;
        }
        else{
            System.arraycopy(arr, 0 ,temp, 0, position);
            temp[position] = item;
            System.arraycopy(arr, position, temp, position + 1, size - position);
            return temp;
        }
    }

    public static void reverse(int[] arr){
        // solution 1: use a new array
//        int[] temp = new int[arr.length];
//        for(int i = 0; i < arr.length; i++) {
//            temp[i] = arr[arr.length - i - 1];
//        }
//        for(int i = 0; i < arr.length; i++) {
//            arr[i] = temp[i];
//        }

        // solution 2: use half array
        for(int i = 0; i < arr.length / 2; i++){
            int index = arr.length - i - i;
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    /** Write a non-destructive method replicate(int[] arr) that
     * replaces the number at index i\ with arr[i] copies of itself.
     * For example, replicate([3, 2, 1]) would return [3, 3, 3, 2, 2, 1].*/
    public static int[] replicate(int[] arr){
        int len = 0;
        for(int i : arr){
            len += i;
        }
        int[] temp = new int[len];
        int start = 0;
        for(int item : arr){
            for(int j = 0; j < item; j++){
                temp[start] = item;
                start ++;
            }
        }
        return temp;
    }

    public static int[] flatten(int[][] x){
        int len = 0;
        int index = 0;
        for(int[] arr : x){
            for(int item : arr){
                len++;}}
        int[] temp = new int[len];

        for(int[] arr : x){
            for(int item : arr){
                temp[index] = item;
                index++;
            }
        }
        return temp;
    }

    public static void main(String[] args){
        int[][] temp = new int[][]{{1}, {},{1,1,1},{1,1,1,1}};
    }
}
