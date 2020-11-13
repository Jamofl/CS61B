public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y){
        Character c1 = x;
        Character c2 = y;

        //if (Math.abs(c1.compareTo(c2)) == 1){
        if (Math.abs(x - y) == 1){
            return true;
        }
        return false;
    }
}
