import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double ratio;

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        ratio = 0.0;
    }

    /** reduce the length of items to half*/
    public void shrink(){
        int capacity = items.length / 2;
        T[] temp = (T[]) new Object[capacity];
        if (nextFirst <= nextLast){
            System.arraycopy(items, moveRight(nextFirst), temp,1, nextLast - nextFirst - 1);
            nextLast = nextLast - nextFirst;
            nextFirst = 0;
            this.items = temp;
        }
        else{
            System.arraycopy(items, 0 , temp,0, nextLast);
            System.arraycopy(items, moveRight(nextFirst) , temp, moveRight(capacity - (items.length - nextFirst)), items.length - nextFirst - 1);
            nextFirst = capacity - (items.length - nextFirst);
            this.items = temp;
        }

    }

    public void autoResize(){
        //computeRatio();
        this.ratio = (float)size / items.length;
        if (ratio < 0.25){
            shrink();
        }
    }

    public int moveLeft(int index){
        if (index == 0)
            index = items.length - 1;
        else
            index -= 1;
        return index;
    }

    public int moveRight(int index){
        if (index == items.length - 1)
            index = 0;
        else
            index += 1;
        return index;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if (nextFirst <= nextLast){
            System.arraycopy(items, moveRight(nextFirst), temp, moveRight(nextFirst), size);
        }
        else{
            System.arraycopy(items, 0, temp, 0, nextLast);
            System.arraycopy(items, moveRight(nextFirst),
                    temp, moveRight(capacity - (items.length - nextFirst)),
                    items.length - nextFirst - 1);
            this.nextFirst = capacity - (items.length - nextFirst);

        }
        items = temp;
    }

    @Override
    public void addFirst(T item){
        if (size == items.length - 2){ // resize
            resize((items.length) * 2);
        }
        items[nextFirst] = item;
        nextFirst = moveLeft(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item){
        if (size == items.length - 2){ // resize
            resize((items.length) * 2);
        }
        items[nextLast] = item;
        nextLast = moveRight(nextLast);
        size += 1;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        if (nextFirst <= nextLast){
            for (int i = moveRight(nextFirst); i < moveRight(nextFirst) + size; i ++)
                System.out.print(items[i] + " ");
        }
        else{
            for(int i = nextFirst + 1; i <= items.length - 1; i ++)
                System.out.print(items[i] + " ");
            for(int i = 0; i <= nextLast - 1; i ++){
                System.out.print(items[i] + " ");
            }
        }
    }

    @Override
    public T removeFirst(){
        int index = moveRight(nextFirst);
        T item = get(index);
        items[index] = null;
        nextFirst = index;
        size -= 1;
        autoResize();
        return item;
    }

    @Override
    public T removeLast(){
        int index = moveLeft(nextLast);
        T item = get(index);
        items[index] = null;
        nextLast = index;
        size -= 1;
        autoResize();
        return item;
    }

    @Override
    public T get(int index){
        return items[index];
    }

    public static void main(String[] args){
        ArrayDeque q = new ArrayDeque();
        q.addFirst(1);
        q.addLast(2);
        q.addLast(3);
        q.addLast(4);
        q.addLast(5);
        q.addLast(6);
        q.addLast(7);
        q.addLast(8);




//        for(int i = 4; i <= 11; i ++){
//            q.addLast(i);
//        }
//
//        q.printDeque();
//        System.out.println();
//        for( int i = 0; i <= 8; i ++){
//            q.removeFirst();
//        }
//        q.printDeque();

    }
}
