/** Demonstrates creation of a method in Java */
public class LargerNumber {
    /** Return the larger number of x and y. */
    public static int larger(int x, int y){
        if (x > y)
            return x;
        else
            return y;
    }

    public static void main(String[] args) 
    {
        System.out.println("bigger one is " + larger(-1, 2));
    }

}