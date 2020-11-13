//import javax.lang.model.util.ElementScanner6;
public class Intlist{

    public int first;// instance member
    public Intlist rest;// instance member

    public Intlist(int f, Intlist r){
        first = f;
        rest = r;
    }

    /** return the kth element in the list */
    public int Get(int k){
        if (k == 0)
            return this.first;
        else
            return this.rest.Get(k - 1);

    }
    /**return the size of a list using recursion */
    public int Size(){
        if (this.rest == null)
            return 1;
        else
            return 1 + this.rest.Size();
    }
    /** return the size of a list using iteration */
    // public int iterativeSize(){
    //     int size = 1;
    //     Intlist p = this;
    //     while (p.rest != null)
    //     {
    //         size += 1;
    //         p = p.rest;
    //     }
    //     return size;
    // }

    /** return a new list with every element increment  by k ,non destructive */ 
    public static Intlist incrList(Intlist l, int k){
        if (l == null)
            return null;
        else
            return new Intlist(l.first + k, incrList(l.rest, k));
    }

    // /** return a new list point to l with every element increment by k,destructive*/
    public static Intlist dincrList(Intlist l, int k){
        Intlist temp = l;

        while (temp != null)
        {
            temp.first = temp.first + k;
            temp = temp.rest;
        }
        
        return l;

     }
    

    public static void main(String[] args){
        Intlist L = new Intlist(3, null);
        L = new Intlist(2, L);
        L = new Intlist(1, L);
        //System.out.println(L);
        Intlist t =  Intlist.dincrList(L, 1);
        
        System.out.println(L.first);
        System.out.println(L.rest.first);
        System.out.println(L.rest.rest.first);

        System.out.println();

        System.out.println(t.first);
        System.out.println(t.rest.first);
        System.out.println(t.rest.rest.first);
        
        
    }
}