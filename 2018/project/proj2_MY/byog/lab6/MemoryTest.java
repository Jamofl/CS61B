package byog.lab6;

import org.junit.Test;

public class MemoryTest {
    @Test
    public void testgenerateRandomString(){
        MemoryGame game = new MemoryGame(12345l, 30, 30);
        String s = game.generateRandomString(4);
        System.out.println(s);

    }

    @Test
    public void testDrawFrame(){
        MemoryGame game = new MemoryGame(123l, 30, 30);
//        game.drawFrame("abc", 1000);
//        game.flashSequence("abcd");
        String s = game.solicitNCharsInput(4);
        System.out.println(s);
    }

    @Test
    public void testStringEqual() {
        String s = "123";
        String t = "123";
        System.out.println(s.equals(t));

    }
}
