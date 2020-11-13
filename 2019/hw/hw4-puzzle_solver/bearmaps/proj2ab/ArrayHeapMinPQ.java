package bearmaps.proj2ab;
import bearmaps.proj2ab.ExtrinsicMinPQ;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<Node> minheap; // a Node list
    private int size;
    private Map<T, Double[]> map; // a map store all item and corresponding priority and index

    private class Node{
        private T item;
        private double priority;
        private int index;
        public Node(T item, double priority, int index){
            this.item = item;
            this.priority = priority;
            this.index = index;
        }

        public void setPriority(double newPriority){
            this.priority = newPriority;
        }

        // quite important, override equals, so we can find the index of the node want
        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return (this.priority == ((Node)o).priority) &&
                        (this.item.equals(((Node)o).item));
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ(){
        size = 0;
        map = new HashMap<>();
        minheap = new ArrayList<>();
        minheap.add(new Node(null, Integer.MIN_VALUE, 0)); // first element, index 0
    }

    private Node getParent(Node n){
        if (n == null)
            return null;
        int index = n.index;
        if (index <= 1)
            return n;
        else
            return minheap.get(index / 2);
    }
    private Node getLeftChild(Node n){
        if(n == null)
            return null;
        int index = n.index;
        if (index * 2 > size())
            return null;
        else
            return minheap.get(index * 2);
    }
    private Node getRightChild(Node n){
        if(n == null)
            return null;
        int index = n.index;
        if (index * 2 + 1> size())
            return null;
        else
            return minheap.get(index * 2 + 1);
    }

    // O(1)
    private void exchange(Node S, Node T){ // exchange item Node S and Node T
        int index1 = S.index;
        int index2 = T.index;
        minheap.set(index1, T);
        minheap.set(index2, S);
        S.index = index2;
        T.index = index1;
        Double[] attrS = {S.priority, (double) index2};
        Double[] attrT = {T.priority, (double) index1};
        map.put(S.item, attrS);
        map.put(T.item, attrT);
    }

    // Return smaller child, if there is no child, return null;
    // if there is only one child, return that child
    private Node getBiggerChild(Node node){
        Node leftChild = getLeftChild(node);
        Node rightChild = getRightChild(node);
        if(leftChild == null)
            return rightChild;
        else if(rightChild == null)
            return leftChild;
        else
            return (leftChild.priority < rightChild.priority ? rightChild : leftChild);
    }
    private Node getSmallerChild(Node node){
        Node leftChild = getLeftChild(node);
        Node rightChild = getRightChild(node);
        if(leftChild == null)
            return rightChild;
        else if(rightChild == null)
            return leftChild;
        else
            return (leftChild.priority <= rightChild.priority ? leftChild : rightChild);
    }

    // O(logN)
    private void swim(Node node){
        if (node == null)
            return;
        Node parent = getParent(node);
        if(parent.priority > node.priority){
            exchange(parent, node);
            swim(node);
        }
    }

    // O(logN)
    private void sink(Node node){
        if (node == null)
            return;
        Node smallerChild = getSmallerChild(node);
        if (smallerChild == null)
            return;
        else if(node.priority > smallerChild.priority){
            exchange(node, smallerChild);
            sink(node);
        }
    }

    //O(1)
    private Node find(T item){
        int index = map.get(item)[1].intValue();
        Node target = minheap.get(index);
        return target;
    }

    //find the node whose item is ITEM, staring at node NODE
    private Node find(Node node, T item){
        Node target = new Node(item, map.get(item)[0], 0);
        Node smallerChild = getSmallerChild(node);
        Node biggerChild = getBiggerChild(node);
        if (target.item.equals(node.item))
            return node;
        else if (smallerChild == null && biggerChild == null)
            return null;
        else if (target.item.equals(smallerChild.item))
            return smallerChild;
        else if (target.item.equals(biggerChild.item))
            return biggerChild;
        else if(target.priority < smallerChild.priority || target.priority < node.priority)
            return null;
        else{
            Node n1 = find(smallerChild, item);
            Node n2 = find(biggerChild, item);
            if (n1 != null)
                return n1;
            else if (n2 != null)
                return n2;
            else
                return null;
        }
    }

    public T[] getList(){ //return a T[] containing all items,including the 0th node's item
        T[] result = (T[])(new Object[size() + 1]);
        for(int i = 0; i <= size(); i ++){
            result[i] = minheap.get(i).item;
        }
        return result;
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    // O(logN)
    public void add(T item, double priority){
        if (contains(item))
            throw new IllegalArgumentException();

        Node temp = new Node(item, priority, size() + 1);
        minheap.add(temp);
        size ++;
        swim(temp);
        Double[] attr = {priority, (double)size()};
        map.put(item, attr);
    }

    /* Returns true if the PQ contains the given item. */
    // O(1)
    public boolean contains(T item){
        return map.get(item) != null;
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    // O(1)
    public T getSmallest(){
        if (size() == 0)
            throw new NoSuchElementException();
        return minheap.get(1).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    // O(logN)
    public T removeSmallest(){
        if (size() == 0)
            throw new NoSuchElementException();
        Node smallestNode = minheap.get(1);
        if(size() == 1){
            minheap.remove(1);
            size --;
        }
        else{
            exchange(minheap.get(1), minheap.get(size()));
            minheap.remove(size());
            size --;
            sink(minheap.get(1));
        }
        map.remove(smallestNode.item);
        return smallestNode.item;
    }
    /* Returns the number of items in the PQ. */
    // O(1)
    public int size(){
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    // O(logN)
    public void changePriority(T item, double priority){
        if (!contains(item))
            throw new NoSuchElementException();

        Node target = find(item);
        target.setPriority(priority);
        sink(target);
        swim(target);
    }

    public static void main(String[] args){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ();
        pq.add("a", 0);
        pq.add("b", 1);
        pq.add("c", 2);
        pq.add("d", 3);
        pq.add("e", 4);
        pq.add("f", 5);
        pq.add("g", 6);
        pq.add("h", 7);
        pq.add("i", 8);
        pq.add("j", 9);
        pq.add("k", 10);

        pq.removeSmallest();

        pq.changePriority("c",1);
        System.out.println(pq.size());
        System.out.println(pq.contains("a"));
        System.out.println(pq.contains("z"));
        System.out.println(pq.removeSmallest());
        System.out.println(pq.getSmallest());
        System.out.println();


    }
}