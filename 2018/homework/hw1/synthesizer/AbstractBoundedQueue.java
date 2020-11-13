package synthesizer;

import java.util.Iterator;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount;
    protected int capacity;

    public int capacity(){
        return capacity;
    }

    public int fillCount(){
        return fillCount;
    }

//    public boolean isEmpty();
//    public boolean isFull();


//    public abstract T peek();
//    public abstract T dequeue();
//    public abstract void enqueue(T x);
//    Iterator<T> iterator();


    // implicitly inherit isEmpty, isFull(default concrete methods)
    // and peek, dequeue, enqueue(abstract methods) and iterator from interface BoundedQueue


}
