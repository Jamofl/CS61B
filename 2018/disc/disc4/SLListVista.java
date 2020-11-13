import java.util.NoSuchElementException;
public class SLListVista extends SLList{
    public SLListVista(){
        super();
    }

    // public int indexOf(int x){
    //     if(this.first == x){
    //         return 0;
    //     }
    //     else if(this.next == null){
    //         throw new NoSuchElementException;
    //     }
    //     else{
    //         return 1 + this.next.indexOf(x);
    //     }
    // }

    // a more clever solution!
    @Override
    public int indexOf(int x){
        int index = super.indexOf(x);
        if (index == -1)
            throw new NoSuchElementException;
        else
            return index;
        
    }

    
}