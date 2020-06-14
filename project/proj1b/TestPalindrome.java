import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne oo = new OffByOne();

    @Test
    public void testWordToDeque() {
        String s = "abcdefghij";
        Deque d = palindrome.wordToDeque(s);
        String actual = "";
        for (int i = 0; i < s.length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals(s, actual);
    }

    @Test
    public void testIspalindrome(){
        String s1 = "abcddsffsdf";
        String s2 = "abcdcba";
        String s3 = "abcddcba";
        String s4 = "a";
        String s5 = "";
        assertFalse(palindrome.isPalindrome(s1));
        assertTrue(palindrome.isPalindrome(s2));
        assertTrue(palindrome.isPalindrome(s3));
        assertTrue(palindrome.isPalindrome(s4));
        assertTrue(palindrome.isPalindrome(s5));


    }

    @Test
    public void testIspalindromeOffByOne(){
        String s1 = "agcfb";
        String s2 = "agcdfb";
        String s3 = "abcddcba";
        String s4 = "a";
        String s5 = "";
        assertTrue(palindrome.isPalindrome(s1, oo));
        assertTrue(palindrome.isPalindrome(s2, oo));
        assertFalse(palindrome.isPalindrome(s3, oo));
        assertTrue(palindrome.isPalindrome(s4, oo));
        assertTrue(palindrome.isPalindrome(s5, oo));


    }
}
