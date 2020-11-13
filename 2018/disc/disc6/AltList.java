import javax.swing.*;

public class AltList<X, Y> {

    private X item;
    private AltList<Y, X> next;

    AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }

    public AltList pairsSwapped(){
        AltList<X, Y> temp = this;
        AltList<Y, X> toReturn = null;
        AltList<Y, X> tempReturn = null;
        AltList last = null;

        toReturn = new AltList<Y, X>(temp.next.item, new AltList<X,Y>(temp.item, null));
        last = toReturn.next;
        temp = next.next;
        while (temp != null){
            tempReturn = new AltList<Y, X>(temp.next.item, new AltList<X,Y>(temp.item, null));
            last.next = tempReturn;
            last = last.next.next;
            temp = temp.next.next;
        }
        return toReturn;
    }

    public AltList<Y, X> pairsSwappedRec(){
        AltList<Y, X> toReturn = new AltList<Y, X>(this.next.item, new AltList<X, Y>(this.item, null));
        if (this.next.next != null){
            toReturn.next.next = this.next.next.pairsSwappedRec();
        }
        return toReturn;
    }


    public static void main(String[] args){
        AltList<Integer, String> lst = new AltList<Integer, String>(5,
                new AltList<String, Integer>("cat",
                        new AltList<Integer, String>(10,
                                new AltList<String, Integer>("dog", null))));
        AltList new_lst = lst.pairsSwapped();
    }


}