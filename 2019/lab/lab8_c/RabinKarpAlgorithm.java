import java.util.ArrayList;
import java.util.List;

public class RabinKarpAlgorithm {



    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int n = input.length();
        int m = pattern.length();
        RollingString rs1 = new RollingString(input.substring(0, m), m);
        RollingString rs2 = new RollingString(pattern, m);

        if (n < m)
            return -1;
        int patternHash = rs2.hashCode();  // θ(m)
        for(int i = 0 ; i < n - m ; i ++){ // n * θ(1) = θ(n)
            int tempHash = rs1.hashCode(); // θ(m) or  θ(1)
            if (tempHash == patternHash){  // θ(1)
                String temp = input.substring(i, i + m);
                if (temp.equals(pattern))
                    return i;
            }
            rs1.addChar(input.charAt(m + i)); // θ(1)
        }
        return -1;
    }

}
