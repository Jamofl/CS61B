public class DrawTriangle {
    public static void draw_tri(int n ){
        int row = 1, col = 1;
        String s = "*";
        while (row < n){
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

    public static void main(String[] args) {
        draw_tri(10);
    }
}