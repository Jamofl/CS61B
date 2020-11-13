public class OffByN implements CharacterComparator {
    private int n;

    public  OffByN(int N){
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y){
        if (Math.abs(x - y) == n){
            return true;
        }
        return false;
    }
}
