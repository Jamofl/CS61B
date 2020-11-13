// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek.(index of first item) */
    private int first;
    /* Index for the next enqueue.(index of last item + 1) */
    private int last;
    /* Array for storing the buffer data. */
    private T[] ring;

    @Override
    public Iterator<T> iterator(){
        return new iter();
    }

    // helper private class
    private class iter implements Iterator<T> {

        private int position;
        private boolean hasChecked;
        // when the ring is full and first == last,
        // check if it is the first time that position has gone through position first already

        public iter(){
            position = first;
            hasChecked = false;
        }

        @Override
        public boolean hasNext() {
            if (fillCount == 0)
                return false;
            if( !hasChecked  && position == first){
                hasChecked = true;
                return true; }
            if (position == last)
                return false;
            return true;
        }

        @Override
        public T next(){
            T toReturn = ring[position];
            position = moveRight(position);
            return toReturn;
        }
    }


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        ring = (T[]) new Object[capacity];
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    private int moveRight(int index){
        int temp = index;
        temp ++;
        if(temp == this.capacity)
            temp = 0;
        return temp;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (this.fillCount == this.capacity)
        {
            throw new RuntimeException("Ring buffer overflow");
        }
        else{
            ring[last] = x;
            last = moveRight(last);
            this.fillCount ++;
            // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (this.fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }
        else{
            T toReturn = ring[first];
            ring[first] = null;
            first = moveRight(first);
            this.fillCount --;
            return toReturn;
            // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (this.fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }
        else
            return ring[first];
        // TODO: Return the first item. None of your instance variables should change.
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
