public class LinkedListDeque<T> {
    private class TNode{
        public T item;
        public TNode next;
        public TNode pre;

        public TNode(T i, TNode n, TNode p){
            item = i;
            next = n;
            pre = p;

        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque(T x){
        sentinel = new TNode(null, null, null);
        TNode temp = new TNode(x, sentinel, sentinel);
        sentinel.pre = temp;
        sentinel.next = temp;
        size = 1;
    }

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public void addFirst(T x){
        TNode temp = new TNode(x, sentinel.next, sentinel);
        sentinel.next.pre = temp;
        sentinel.next= temp;
        size += 1;
    }

    public void addLast(T x){
        TNode temp = new TNode(x, sentinel, sentinel.pre);
        sentinel.pre.next = temp;
        sentinel.pre = temp;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        TNode t = sentinel.next;
        while (t != sentinel){
            System.out.print(t.item + " ");
            t = t.next;
        }
    }

    public T removeFirst(){
        T i = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return i;

    }

    public T removeLast(){
        T i = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size -= 1;
        return i;
    }

    public T get(int index){
        TNode t = sentinel.next;
        while (index != 0){
            t = t.next;
            index -= 1;
        }
        return t.item;
    }

    public T getrec(int index, TNode t){
        if (index == 0){
            return t.item;
        }
        else{
            return getrec(index - 1, t.next);
        }
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> l = new LinkedListDeque(1);
        l.addFirst(0);
        l.addLast(2);
        l.addLast(3);
        l.printDeque();

        l.removeLast();
        l.printDeque();

        l.removeFirst();

        l.printDeque();
        System.out.println(l.size());
        l.get(0);
        l.get(1);
        l.getrec(1, l.sentinel.next);
    }

}
