package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{

    //Iterator<T> iterator();

    // return size of buffer
    int capacity();

    // return number of items currently in the buffer
    int fillCount();

    // add item to the end
    void enqueue(T x);

    // pop and return item from the front
    T dequeue();

    // just return item from the front(without deleting)
    T peek();

    default boolean isEmpty(){
        return fillCount() == 0;
    }

    default boolean isFull(){
        return fillCount() == capacity();
    }
}
