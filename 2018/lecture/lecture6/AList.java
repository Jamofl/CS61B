/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class AList<item> {
    private int size;
    private item[] items;
    

    /** Creates an empty list. */
    public AList() {
        size = 0;
        items = (item[])new Object[100]; // cast
        // java not allow generics array
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        item[] temp = (item[]) new Object[capacity];
        System.arraycopy(items,0, temp, 0, size);
        items = temp;
    }

    /** Inserts X into the back of the list. */
    public void addLast(item x) {
        if (size == items.length){
            resize(size * 2);
//            items[size] = x;
//            size += 1;
//            int[] temp = new int[size + 1];
//            System.arraycopy(items,0, temp, 0, size);
//            items = temp;
        }
        items[size] = x;
        size += 1;

    }

    /** Returns the item from the back of the list. */
    public item getLast() {
        return items[size - 1];
        
    }
    /** Gets the ith item in the list (0 is the front). */
    public item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public item removeLast() {
        item last = getLast();
        //items[size - 1] = 0; not necessary
        items[size - 1] = null; // avoid loiter(waste memory)
        size -= 1;
        return last;

    }

    public static int[] insert(int[] arr, int item, int position){
        int size = arr.length;
        int[] temp = new int[size + 1];
        if(position >= size){
            System.arraycopy(arr,0, temp, 0, size);
            temp[size] = item;
            return temp;
        }
        else{
            System.arraycopy(arr, 0 ,temp, 0, position);
            temp[position] = item;
            System.arraycopy(arr, position, temp, position + 1, size - position);
            return temp;
        }
    }
} 