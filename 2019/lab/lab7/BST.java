import javafx.beans.binding.ObjectExpression;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BST<K extends Comparable<K>, V>{

    private class Node{
        private Node left, right;
        private V value;
        private K key;
        private int size;

        public Node(K key, V value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private Node root;
    private Set<K> set;

    public BST(){
        set = new HashSet<>();
    }

    public Set getKeys(){
        return set;
    }
    public int size(){
        return size(this.root);
    }

    private int size(Node N){
        if (N == null)
            return 0;
        else
            return N.size;
    }

    public void clear(){
        Set set = this.getKeys();
        Set set2 = new HashSet(set); // can not change the set while iterating it at the same time
        for(Object key : set2){
            this.delete((K)key);
        }
    }

    public void clearRec(){
        if(this.size() != 0){
            this.delete(this.root.key);
            this.clearRec();
        }
        else
            return;
    }

    private void clearIter(){
        while(this.size() != 0){
                this.delete(this.root.key);
                set = this.getKeys();
        }
    }

    public V get(K key){
        return getHelper(this.root, key);
    }

    private V getHelper(Node N, K key){
        if (N == null)
            return null;
        if(key.compareTo(N.key) > 0)
            return getHelper(N.right, key);
        else if (key.compareTo(N.key) < 0)
            return getHelper(N.left, key);
        else
            return N.value;
    }

    public void insert(K key, V value){
        set.add(key);
        this.root = insertHelper(this.root, key, value);
    }

    // 不使用root的方法存在错误，最后的T指向叶子节点，而不是树本身，会失去对树本身的指针。
    private Node insertHelper(Node N, K key, V value){
        if (N == null)
            return new Node(key, value, 1);
        else if (key.compareTo(N.key) > 0)
            N.right = insertHelper(N.right, key, value);
        else if (key.compareTo((K) N.key) < 0)
            N.left = insertHelper(N.left, key, value);
        else
            N.value = value;
        N.size = size(N.left) + size(N.right) + 1;
        return N;
    }

    public BST delete(K key){
        set.remove(key);
        this.root = deleteHelper(this.root, key);
        return this;
    }

    private Node deleteHelper(Node N, K key){
        if (N == null)
            return null;
        else if (key.compareTo(N.key) > 0){
            N.right = deleteHelper(N.right, key);
        }
        else if (key.compareTo(N.key) < 0){
            N.left = deleteHelper(N.left, key);
        }
        else{
            if(N.left == null)
                return N.right;
            else if (N.right == null)
                return N.left;
            else{
                Node successor = findSmallest(N.right);// the smallest node on the right sub tree
                K k = successor.key;
                V v = successor.value;
                this.deleteHelper(N, successor.key);
                N.key = k;
                N.value = v;
            }
        }
        N.size = size(N.left) + size(N.right) + 1;
        return N;
    }

    // find the Node with smallest key in Node N;
    private Node findSmallest(Node N){
        if(N.left == null)
            return N;
        else{
            return findSmallest(N.left);
        }
    }




}

