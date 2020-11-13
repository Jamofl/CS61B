public class BadIntegerStack {

    public class Node{

        public Integer value;
        public Node prev;

        public Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    private Node top;
    public boolean isEmpty() {
        return top == null;
    }
    public void push(Integer num) {
        top = new Node(num, top);
    }
    public Integer pop() {
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }
    public Integer peek() {
        return top.value;
    }


    //Exploit the flaw by filling in the main method below so that it prints “Success”
    // by causing BadIntegerStack to produce a NullPointerException.
    public static void main(String[] args) {
        try{
            BadIntegerStack stack = new BadIntegerStack();
            //stack.push(3);
            stack.pop();
            //stack.peek();
        }
        catch (NullPointerException ne){
            System.out.println("Success");
        }
    }

    //Exploit another flaw in the stack by completing the main method below so that
    //the stack appears infinitely tall.
//    public static void main(String[] args) {
//
//        BadIntegerStack stack = new BadIntegerStack();
//        stack.push(3);
//        stack.push(2);
//        stack.top.prev.prev = stack.top;
//    }


}