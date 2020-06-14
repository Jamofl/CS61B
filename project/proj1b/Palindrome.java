public class Palindrome {

    // change a word to a deque
    public Deque<Character> wordToDeque(String word){
        //Deque<Character> l = new LinkedListDeque<Character>();
        Deque<Character> l = new ArrayDeque<Character>();
        char c;
        for(int i = 0; i < word.length(); i++){
            c = word.charAt(i);
            l.addLast(c);
        }
        return l;
    }

    // overload
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> w = wordToDeque(word);
        return isPalindromeRecOffByOne(w, cc);
    }

    public boolean isPalindromeRecOffByOne(Deque w, CharacterComparator cc){
        if (w.size() == 0 || w.size() == 1)
            return true;
        else {
            return (cc.equalChars((char)w.removeLast(), (char)w.removeFirst())) && isPalindromeRecOffByOne(w, cc);
            // first == last and w without first and last is also a palindrome
        }

    }

    public boolean isPalindrome(String word){
        // solution 1
//        if (word == "" || word.length() == 1)
//            return true;
//        Deque<Character> w = wordToDeque(word);
//        Deque<Character> copy = wordToDeque(word); // a copy of w, used to remove without destroy w
//        for(int i = 0; i < (w.size() / 2); i++){
//            if (copy.removeFirst() != copy.removeLast())
//                return false;
//        }
//        return true;

        // solution 2
        Deque<Character> w = wordToDeque(word);
        return isPalindromeRec(w);

    }
    public boolean isPalindromeRec(Deque w){
        if (w.size() == 0 || w.size() == 1)
            return true;
        else {
            return (w.removeLast() == w.removeFirst()) && isPalindromeRec(w);
            // first == last and w without first and last is also a palindrome
        }
    }
}
