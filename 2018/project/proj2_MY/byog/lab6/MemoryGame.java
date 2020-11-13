package byog.lab6;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.math.RoundingMode;
import java.nio.file.LinkOption;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(seed,40, 40);
        game.startGame();
    }

    public MemoryGame(Long seed, int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);

    }

    public String generateRandomString(int n) {
        String str = "";
        int randomNum;
        while( n > 0){
            randomNum = rand.nextInt(26); // from 0 to 25
            str = str + CHARACTERS[randomNum];
            n --;
        }
        return str;
        //TODO: Generate random string of letters of length n

    }

    // show character for a certain period of time
    public void drawFrame(String s) {
        StdDraw.clear();
        StdDraw.clear(Color.black);

        //TODO: If game is not over, display relevant game information at the top of the screen
        if(!gameOver){
            Font smallFont = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(smallFont);
            StdDraw.textLeft(1, this.height - 1, String.valueOf(round));
            StdDraw.text(this.width / 2, this.height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(this.width - 1, this.height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 2, width, height - 2);

        }
        //TODO: Take the string and display it in the center of the screen
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.yellow);

        if (s != null) {
            StdDraw.text(this.width / 2, this.height / 2, s);}

        StdDraw.show();


    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(char c : letters.toCharArray()){
            drawFrame(String.valueOf(c));
            StdDraw.pause(1000);
            drawFrame(null);
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        drawFrame(input);
        //TODO: Read n letters of player input

        while (input.length() < n){
            if (! StdDraw.hasNextKeyTyped()){
                continue;
            }
            else{
                char c = StdDraw.nextKeyTyped();
                input += String.valueOf(c);
                drawFrame(input);
                System.out.println(input);
            }
        }
        StdDraw.pause(500);
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        gameOver = false;
        playerTurn = false;
        String STARTMSG;
        String FAILMSG = "Game Over! You made it to round ";
        String RANDOMSTR;
        String USERTYPE;


        //TODO: Establish Game loop
        while(true){
            try{
                playerTurn = false;
                STARTMSG = "Round: " + round + " Good luck!";
                drawFrame(STARTMSG);
                Thread.sleep(1500);
                RANDOMSTR =  generateRandomString(round);
                flashSequence(RANDOMSTR);
                playerTurn = true;
                USERTYPE = solicitNCharsInput(round);
                if(!USERTYPE.equals(RANDOMSTR)){
                    gameOver = true;
                    drawFrame(FAILMSG + round);
                    break;
                }
                else{
                    drawFrame("Correct! Good job!");
                    Thread.sleep(1000);
                }
                round ++;
            }
            catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    }

}
