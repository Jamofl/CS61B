import java.util.LinkedList;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;
    //static final int UNIQUECHARS = 256;


    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;
//    static final int PRIMEBASE = 101;

    public int len;
    public LinkedList<Character> stringList;
    public boolean isHashed;
    public int preHashValue;
    public int preFirstDigitHash;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        /* FIX ME */
        //this.s = s;
        this.len = length;
        this.isHashed = false;
        this.preHashValue = 0;
        stringList = new LinkedList<Character>();
        for (int i = 0; i < len; i ++ ){
            stringList.add(s.charAt(i));
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) { // θ(1)
        /* FIX ME */
        stringList.removeFirst();
        stringList.add(c);
    }

    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() { // θ(n)
        StringBuilder strb = new StringBuilder(this.len);
        /* FIX ME */
        for(char c : stringList){
            strb.append(c);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return this.len;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        if (this == o)
            return true;
        else if (o == null)
            return false;
        else{
            RollingString that = (RollingString) o;
            return this.toString().equals(o.toString());
        }
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        if (!isHashed){
            for(int i = 0; i < len; i ++){
                this.preHashValue += Math.floorMod((int)(this.stringList.get(i) * Math.pow(UNIQUECHARS,len - 1 - i)), PRIMEBASE);
            }
            isHashed = true;
            this.preHashValue = Math.floorMod(this.preHashValue, PRIMEBASE);
        }
        else{
            int newHashValue = Math.floorMod((this.preHashValue - preFirstDigitHash)
                    * UNIQUECHARS + this.stringList.getLast(), PRIMEBASE);
            this.preHashValue = newHashValue;
        }
        this.preFirstDigitHash = this.stringList.get(0) * helper(UNIQUECHARS, PRIMEBASE,len - 1);
        return this.preHashValue;

    }
    private int helper(int a, int b, int n){
        int x = 1;
        while (n > 0){
            x = Math.floorMod(x * a, b);
            n --;
        }
        return x;
    }

    public static void main (String[] args){
        RollingString s = new RollingString("bcee", 4);
        System.out.println(s.stringList);
        System.out.println(s.hashCode());
        s.addChar('e');
        System.out.println(s.stringList);
        System.out.println(s.hashCode());
        //System.out.println( Math.floorMod(-71, 101));

    }
}
