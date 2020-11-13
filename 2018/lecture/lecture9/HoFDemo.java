/**
 * Created by hug on 2/6/2017.
 * Demonstrates higher order functions in Java.
 */
public class HoFDemo {


    // an old way of doing HOF in java(it still works without the interface)
    public static int twice(IntUnaryFunction f, int x){
        return f.apply(f.apply(x));
    }


    public static void main(String[] args){
        System.out.println(twice(new SquareX(),3));
    }
}

