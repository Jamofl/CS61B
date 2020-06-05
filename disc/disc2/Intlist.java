import java.io.IOError;

public class Intlist {
    int first;
    Intlist rest;
    public Intlist(int first, Intlist rest){
        this.first = first;
        this.rest = rest;
    }

    public static void main(String[] args){
        Intlist l = new Intlist(3, null);
        l = new Intlist(2, l);
        l = new Intlist(1, l);
        Intlist t1 = square(l);
        Intlist t2 = squareMutative(l);

    }

    // non-destructive
    public static Intlist square(Intlist l) {
        if (l == null){
            return l;
        }
        else{
            return new Intlist((l.first) * l.first, square(l.rest));
        }
    }

    // destructive 
    public static Intlist squareMutative(Intlist l) {
        // Intlist temp = l;
        // while (temp != null){
        //     temp.first = temp.first * temp.first;
        //     temp = temp.rest;
        // }
        // return l;
        if (l == null){
            return l;
        }
        else{
            l.first = l.first * l.first;
            l.rest = squareMutative(l.rest);}
        
        return l;
    }


    
}