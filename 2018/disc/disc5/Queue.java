import org.junit.Test;

import java.util.Stack;

public class Queue<T> extends Stack<T>{


        /*
        Define a Queue class that implements the push and poll
        methods of a queue ADT using only a Stack class which implements the stack ADT.
         */

        Stack<T> s = new Stack();
        Stack<T> rev = new Stack();

        @Override
        public synchronized T pop() {
                while (!s.empty()){
                        rev.push(s.pop());
                }
                T temp = rev.pop();
                while (!rev.empty()){
                        s.push(rev.pop());
                }
                return temp;
        }

        @Override
        public T push(T item) {
                return s.push(item);
        }
        // recursion version
//        private Stack<T> stack = new Stack<>();
//        public T push(T e){
//                return stack.push(e);
//        }
//
//        public T pop(){
//                return pop(stack.pop());
//        }
//
//        private T pop(T previous){
//                if (stack.empty()){
//                        return  previous;
//                }
//                else{
//                        T cur = stack.pop();
//                        T toReturn = pop(cur);
//                        push(previous);
//                        return toReturn;
//                }
//        }


        @Test
        public void test(){
                Queue<Integer> q = new Queue<>();
                int t;
                t = q.push(1);
                t = q.push(2);
                t= q.push(3);
                t= q.pop();
                t= q.pop();
        }
}
