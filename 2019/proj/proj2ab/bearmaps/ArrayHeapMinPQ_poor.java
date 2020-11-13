package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ_poor<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<Node> minheap; // a Node list
    private Node node;
    private int size;
    private Map<T, Double> map;

    private class Node{
        private T item;
        private double priority;
        public Node(T item, double priority){
            this.item = item;
            this.priority = priority;
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
    }

    public ArrayHeapMinPQ_poor(){
        size = 0;
        map = new HashMap<>();
        minheap = new ArrayList<>();
        minheap.add(new Node(null, Integer.MIN_VALUE)); // first element, index 0
    }

    private Node getParent(Node n){
        int index = minheap.indexOf(n);
        if (index <= 1)
            return n;
        else
            return minheap.get(index / 2);
    }
    private Node getLeftChild(Node n){
        int index = minheap.indexOf(n);
        if (index * 2 > size())
            return null;
        else
            return minheap.get(index * 2);
    }
    private Node getRightChild(Node n){
        int index = minheap.indexOf(n);
        if (index * 2 + 1> size())
            return null;
        else
            return minheap.get(index * 2 + 1);
    }

    private void exchange(Node S, Node T){ // exchange item Node S and Node T
        int index1 = minheap.indexOf(S);
        int index2 = minheap.indexOf(T);
        minheap.set(index1, T);
        minheap.set(index2, S);
    }

    // Return smaller child, if there is no child, return null;
    // if there is only one child, return that child
    private Node getBiggerChild(Node node){
        Node leftChild = getLeftChild(node);
        Node rightChild = getRightChild(node);
        if(leftChild == null && rightChild == null)
            return null;
        else if(leftChild == null && rightChild != null)
            return rightChild;
        else if(leftChild != null && rightChild == null)
            return leftChild;
        else
            return (leftChild.priority < rightChild.priority ? rightChild : leftChild);
    }
    private Node getSmallerChild(Node node){
        Node leftChild = getLeftChild(node);
        Node rightChild = getRightChild(node);
        if(leftChild == null && rightChild == null)
            return null;
        else if(leftChild == null && rightChild != null)
            return rightChild;
        else if(leftChild != null && rightChild == null)
            return leftChild;
        else
            return (leftChild.priority <= rightChild.priority ? leftChild : rightChild);
    }
    private void swim(Node node){
        Node parent = getParent(node);
        if(parent.priority > node.priority){
            exchange(parent, node);
            swim(node);
        }
    }

    private void sink(Node node){
        Node smallerChild = getSmallerChild(node);
        if (smallerChild == null)
            return;
        else if(node.priority > smallerChild.priority){
            exchange(node, smallerChild);
            sink(node);
        }
    }

    //O(N)
    private Node find(T item){
        int index = minheap.indexOf(new Node(item, map.get(item)));
        Node target = minheap.get(index);
        return target;
    }




    //find the node whose item is ITEM, staring at node NODE
    private Node find(Node node, T item){
        Node target = new Node(item, map.get(item));
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
    public void add(T item, double priority){
        if (contains(item))
            throw new IllegalArgumentException();

        Node temp = new Node(item, priority);
        minheap.add(temp);
        swim(temp);
        size ++;
        map.put(item, priority);
    }


    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item){
        return map.get(item) != null;
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){
        if (size() == 0)
            throw new NoSuchElementException();
        return minheap.get(1).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest(){
        if (size() == 0)
            throw new NoSuchElementException();
        Node smallestNode = minheap.get(1);
        map.remove(smallestNode.item);
        exchange(minheap.get(1), minheap.get(size()));
        minheap.set(size(), null);
        sink(minheap.get(1));
        size --;
        return smallestNode.item;
    }
    /* Returns the number of items in the PQ. */
    public int size(){
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority){
        if (!contains(item))
            throw new NoSuchElementException();
//        Node target = find(minheap.get(1), item);
        Node target = find(item);
        target.setPriority(priority);
        map.put(item, priority);
        sink(target);
        swim(target);
    }

    public static void main(String[] args){
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ();
        pq.add("a", 4);
        pq.add("b", 2);
        pq.add("c", 9);
        pq.add("d", 3);
        pq.add("e", 5);
        pq.add("f", 7);
        pq.add("g", 11);
        pq.add("h", 6);
        pq.add("i", 8);
        pq.add("j", 10);
        pq.add("k", 12);

        pq.changePriority("c",1);
        System.out.println(pq.size());
        System.out.println(pq.contains("a"));
        System.out.println(pq.contains("z"));
        System.out.println(pq.removeSmallest());
        System.out.println(pq.getSmallest());
        System.out.println();




    }
}
