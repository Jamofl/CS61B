
/** an SLList is a list of integers, which hides the terrible truth of the nakedness within */
public class SLListStuff<stuff> {
   
    // make it a private static nested class
    // "private" if only the outside(SLList) class will use it
    // "static" if this inside class(IntNode) never need to use members in outside class(SLList)
    private class StuffNode{
        public stuff item;
        public StuffNode next;
    
        public StuffNode(stuff i, StuffNode n){
            this.item = i;
            this.next = n;
        }
    }

    // access control: make it private so only this class can modify it, all other class cannot
    // private IntNode first; // non-static/instance member
    /** the first item is at sentinel.next(if it exists) */
    private StuffNode first;// non-static/instance member
    private int size = 0;  // non-static/instance member
    //private int max_n = 0; // non-static/instance member
    //private StuffNode last;
 

    // public SLList(){ // empty list
    //     this.first = new StuffNode(-1, null);
    //     //this.last = this.sentinel.next;
    //     this.size = 0;
        
    // }
    public SLListStuff(stuff x){
        this.first = new StuffNode(x, null);
        this.size = 1;
        //this.max_n = x;
    }
    /** return the first item in the list */
    public stuff getFirst(){
        return this.first.item; 
    }
    /** add x to the front of the list */
    public void addFirst(stuff x){
        this.first = new StuffNode(x, first);
        size ++;
        // if (x > max_n)
        //     max_n = x;
    }

    /** add x to the last of the list */
    public void addLast(stuff x){

        // this is not the best way to avoid the null pointer exception
        // an even better way to do is to add sentinel node at the first, which makes 
        // all list the same(has first, who has item and next), more consistent
        // if (this.first == null){
        //     this.addFirst(x);
        // } 
        // else{

        // solution 2: good but still slow
        StuffNode n = this.first;
        while (n.next != null)
            n = n.next;
        n.next = new StuffNode(x, null);
        size ++;

       
    }

    /** private helper function return the length of a SLList which start at IntNode n */
    private int size(StuffNode n, int len){
        if (n.next == null){
            return len;
        }
        else{
            return size(n.next, len + 1);
        }
    }
    /** return length of the list */
    public int size(){
        // solution 1: iteration
        // IntNode n = first;
        // int len = 1;
        // while (n.next != null){
        //     len += 1;
        //     n = n.next;
        // }
        // return len;

        // solution 2: use private recursion helper function
        // return size(this.first, 1);// use private helper function to return the length of first

        // solution 3:
        return this.size; // O(1), no matter how long the input list is.
        
    }

    public static void main(String[] args){
        /* create a list of integer 1 2 3 */
        // write out desired type during declaration
        // use the empty diamond operator <> during instantiation
        SLListStuff<Integer> l = new SLListStuff(0);//Generics 泛型
        l.addFirst(1); 
        l.addFirst(2);
        //l.addLast(6);
        //System.out.println(l.getFirst());
        System.out.println("size: " + l.size());
        System.out.println("first: " + l.getFirst());
        //System.out.println("max: " + l.max_n);
        //System.out.println("last: " + l.last.item);



    }
    
}