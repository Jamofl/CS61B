public class ArrayDeque {

    private int[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double ratio;

    public ArrayDeque(){
        size = 0;
        items = new int[8];
        nextFirst = 0;
        nextLast = 1;
        ratio = 0.0;
    }

    /** reduce the length of items to half*/
    public void shrink(){
        int capacity = items.length / 2;
        int[] temp = new int[capacity];
        if (nextFirst <= nextLast){
            System.arraycopy(items, moveRight(nextFirst), temp,1, nextLast - nextFirst - 1);
            nextLast = nextLast - nextFirst;
            nextFirst = 0;
            this.items = temp;
        }
        else{
            System.arraycopy(items, 0 , temp,0, nextLast);
            System.arraycopy(items, moveRight(nextFirst) , temp, capacity - (items.length - nextFirst) + 1, items.length - nextFirst - 1);
            nextFirst = capacity - (items.length - nextFirst);
            this.items = temp;
        }

    }

    public void autoResize(){
        //computeRatio();
        this.ratio = (float)size / items.length;
        if (ratio < 0.25){
//            resize(items.length / 2);
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
        int[] temp = new int[capacity];
        if (nextFirst <= nextLast){
            System.arraycopy(items,0, temp, 0, size);
        }
        else{
            System.arraycopy(items,0,temp, 0, nextLast);
            System.arraycopy(items, nextFirst + 1,
                    temp, capacity - (items.length - nextFirst) + 1,
                    items.length - nextFirst - 1);
            this.nextFirst = capacity - (items.length - nextFirst);
        }
        items = temp;
    }

    public void addFirst(int item){
        if (size == items.length - 2){ // resize
            resize((items.length) * 2);
        }
        items[nextFirst] = item;
        nextFirst = moveLeft(nextFirst);
        size += 1;
    }

    public void addLast(int item){
        if (size == items.length - 2){ // resize
            resize((items.length) * 2);
        }
        items[nextLast] = item;
        nextLast = moveRight(nextLast);
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

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

    public int removeFirst(){
        int index = moveRight(nextFirst);
        int item = get(index);
        items[index] = 0;
        nextFirst = index;
        size -= 1;
        autoResize();
        return item;
    }

    public int removeLast(){
        int index = moveLeft(nextLast);
        int item = get(index);
        items[index] = 0;
        nextLast = index;
        size -= 1;
        autoResize();
        return item;
    }

    public int get(int index){
        return items[index];
    }

    public static void main(String[] args){
        ArrayDeque q = new ArrayDeque();
        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);


        for(int i = 4; i <= 11; i ++){
            q.addLast(i);
        }

        q.printDeque();
        System.out.println();
        for( int i = 0; i <= 8; i ++){
            q.removeFirst();
        }
        q.printDeque();

    }
}
