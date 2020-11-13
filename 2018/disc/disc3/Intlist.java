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

    // skip the intlist with interval 1, 2, 3, 4...
    // IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    // After calling A.skippify(), A: (1, 3, 6, 10)
    public void skipplify(){
        Intlist l = this;
        int interval = 1;
        int count = 0;
        while(l != null){
              // little complicated and confusing
//            for(int i = 0; i < interval; i++){
//                if (l.rest == null)
//                    return;
//                else if (l.rest.rest == null)
//                    l.rest = null;
//                else
//                    l.rest = l.rest.rest;
//            }
            // easy to understand
            Intlist next = l.rest;
            for(int i = 0; i < interval; i++){
                if(next == null)
                    break;
                    //return;
                next = next.rest;
            }
            l.rest =next;
            l = l.rest;
            interval++;
        }
    }

    public static void removeDuplicate(Intlist p){
        Intlist next = p.rest;
        Intlist temp = p;

        while (next != null){
            if (next.first == temp.first)
                next = next.rest;
            else {
                temp.rest = next;
                temp = temp.rest;
                next = next.rest;}
        }

    }

    public static void main(String[] args){
        Intlist L = new Intlist(8, null);
        //L = new Intlist(9, L);
        //L = new Intlist(8, L);
        L = new Intlist(7, L);
        L = new Intlist(6, L);
        L = new Intlist(6, L);
        L = new Intlist(6, L);
        L = new Intlist(3, L);
        L = new Intlist(3, L);
        L = new Intlist(1, L);
        Intlist.removeDuplicate(L);

    }
}