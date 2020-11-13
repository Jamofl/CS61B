public class UnionFind {

    private int[] lst;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        lst = new int[n];
        for (int i = 0; i < lst.length; i ++){
            lst[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex >= 0 && vertex <= lst.length - 1){

        }
        else{
            throw new IllegalArgumentException();
        }

    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        int root = find(v1);
        int size = - lst[root];
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return lst[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean isconnected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return findFast(v1) == findFast(v2);
    }

    public int findFast(int vertex){
        if(parent(vertex) < 0)
            return vertex;
        lst[vertex] = findFast(parent(vertex));
        return lst[vertex];
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if(find(v1) == find(v2) || v1 == v2)
            return;

        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (size1 > size2){
            lst[root2] = root1;
            lst[root1] -= size2;
        }
        else {
            lst[root1] = find(root2);
            lst[root2] -= size1;
        }
        // TODO
    }


    /* Returns the ROOT of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        if(parent(vertex) < 0)
            return vertex;
        return find(parent(vertex));
    }

}
