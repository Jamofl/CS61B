public class Fib {

    public static int fib(int n) {
        if ((n == 1) || (n == 0))
            return n;
        else
            return fib(n - 1) + fib(n - 2);
    }

    // Extra: Implement fib in 5 lines or fewer. Your answer must be efficient. 
    public static int fib_e(int n, int k, int f0, int f1) {
        if (n == k)
            return f0;
        else{
            return fib_e(n, k + 1, f1, f0 + f1);
        }


    }
    public static void main(String[] args) {
        System.out.println(fib_e(6, 0, 0, 1));

        
    }
    

    
}