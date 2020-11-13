public class Draw {
    public static void main(String[] args) {
        int row = 1, col = 1;
        String s = "*";
        while (row < 6){
            int k = 0;
            while (k < col){
                System.out.print(s);
                k = k + 1;
            }
            System.out.println();
            col = col + 1;
            row = row + 1;
        }
        



    }
 }