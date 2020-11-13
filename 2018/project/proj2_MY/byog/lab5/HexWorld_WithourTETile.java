package byog.lab5;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld_WithourTETile {

    private static  int calMaxLen(int len){
        int lenMiddle = len;
        for(int i = 1; i < len; i++){
            lenMiddle += 2;
        }
        return lenMiddle;
    }

    public static void addHexagon(int len, String signal){
        // len = 3 rows = 6
        drawFront(len,signal);
        drawMiddle(len,signal);
        drawBack(len,signal);

    }
    public static void drawFront(int len, String signal){
        for(int i = 1; i <= len - 1; i ++){
            for(int j = 1; j <= len + i + 1; j ++){
                if(j >= 1 && j <= len - i)
                    System.out.print(" ");
                else
                    System.out.print(signal);
            }
            System.out.println();

        }
    }
    public static void drawMiddle(int len, String signal){
        for(int i = 0; i < 2; i ++){
            for(int j = 0; j < calMaxLen(len); j ++){
                System.out.print(signal);
            }
            System.out.println();
        }

    }
    public static void drawBack(int len, String signal){
        for(int i = len - 1; i >= 1; i --){
            for(int j = 1; j <= len + i + 1; j ++){
                if(j >= 1 && j <= len - i)
                    System.out.print(" ");
                else
                    System.out.print(signal);
            }
            System.out.println();
        }
    }


    public static void main(String[] args){
        addHexagon(3,"a");
    }


}
