import java.util.Stack;

public class SortedStack<T extends Comparable<T>>{

    Stack<T> A;
    Stack<T> B;
    public SortedStack(){
        A = new Stack();
        B = new Stack();
    }


    public T pop() {
        return A.pop();
    }

    public void push(T item) {
        while( !A.isEmpty() && (A.peek().compareTo(item) < 0))
        {
            B.push(A.pop());
        }
        A.push(item);
        while( !B.isEmpty())
            A.push(B.pop());
    }

    public static void main(String[] args){
        SortedStack<Integer> ss = new SortedStack();
        ss.push(3);
        ss.push(1);
        ss.push(4);
        ss.push(2);

        Integer a;
        a = ss.pop();
        a = ss.pop();
        a = ss.pop();
        a = ss.pop();

    }
}
