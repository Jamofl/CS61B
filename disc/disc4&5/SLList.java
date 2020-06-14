public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public SLList(int item){
        first = new IntNode(item, null);
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }


    // Implement SLList.insert which takes in an integer x and inserts it at the given position.
// If the position is after the end of the list, insert the new node at the end.
// Forexample,iftheSLListis5→6→2,insert(10, 1)resultsin5→10→6→2.
    public void insert(int item, int position) {
        IntNode head = first;
        if (first == null || position == 0){
            addFirst(item);
            return;
        }
        while (position != 1 && head.next != null){
            head = head.next;
            position -= 1;
        }
            head.next = new IntNode(item, head.next);
    }

    public void reverse(){
        // solution 1 recursion
        //first = reverseRecursiveHelper(first);

        // solution 2 iteration
        IntNode reversed = null;
        IntNode nextAdd = first;
        while (nextAdd != null){
            IntNode remainder = nextAdd.next;
            nextAdd.next = reversed;
            reversed = nextAdd;
            nextAdd = remainder;
        }
        first = reversed;
    }
    private IntNode reverseRecursiveHelper(IntNode front)
    {
        if (front == null || front.next == null) {
            return front;
    }
        else {
            IntNode reversed = reverseRecursiveHelper(front.next);
            front.next.next = front; // point back to front
            front.next = null; //release the point
        return reversed;
    }
    }
}


