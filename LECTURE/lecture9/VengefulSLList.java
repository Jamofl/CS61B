/** SList with additional operation printLostItems() which prints all items
  * that have ever been deleted. */
public class VengefulSLList<Item> extends SLList<Item> {

    //private SLList<Item> deletedItems = new SLList<>();
    private SLList<Item> deletedItems;


    public VengefulSLList() {// with no argument
        super(); // explicit or implicit, both okay
        deletedItems = new SLList<>();
    }
    public VengefulSLList(Item x) { // with arguments
        super(x); // must call super(x) explicitly
        deletedItems = new SLList<>();
    }

    @Override
    public Item removeLast(){
        Item x = super.removeLast(); // use superclass method - removelast;
        deletedItems.addLast(x);
        return x;
    }

    public void printLostItems(){
        deletedItems.print();
    }
    

    public static void main(String[] args) {

		VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>();
		vs1.addLast(1);
		vs1.addLast(5);
		vs1.addLast(10);
		vs1.addLast(13);
        // vs1 is now: [1, 5, 10, 13] 
        
		vs1.removeLast();
		vs1.removeLast();
		// After deletion, vs1 is: [1, 5]

		// Should print out the numbers of the fallen, namely 10 and 13.
		System.out.print("The fallen are: ");
		vs1.printLostItems();
	}
} 