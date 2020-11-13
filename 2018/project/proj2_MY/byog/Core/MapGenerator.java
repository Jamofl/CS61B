package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {
    TERenderer ter = new TERenderer();
    TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
    /* Feel free to change the width and height. */

    private static final int HEIGHT = 40;
    private static final int WIDTH = 80;
    private static boolean isFirst = true;

    private static long SEED;
    private static Random RANDOM;

    public MapGenerator(long seed){
        RANDOM = new Random(seed);
    }


    public TETile[][] generate() {

        // initialize
        ter.initialize(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        int tileNum = RANDOM.nextInt(3);
        Position startPosition = new Position(5, 0);
        Position nextPoint = null;

        nextPoint =  drawRectangle(finalWorldFrame,  startPosition, 4, 4);

        for (int i = 0; i <= 5; i ++){
            nextPoint = drawVertical(finalWorldFrame, nextPoint);
            nextPoint =  drawRectangle(finalWorldFrame,  nextPoint, 4, 4);
            ter.renderFrame(finalWorldFrame);
        }

;
//        nextPoint =  drawRectangle(finalWorldFrame,  nextPoint, 4, 4);
//        nextPoint = drawHorizontal(finalWorldFrame, nextPoint);
//        ter.renderFrame(finalWorldFrame);
//
//
//        nextPoint =  drawRectangle(finalWorldFrame,  nextPoint, 4,4);
//        nextPoint = drawVertical(finalWorldFrame, nextPoint);
//        ter.renderFrame(finalWorldFrame);
//
//        nextPoint =  drawRectangle(finalWorldFrame,  nextPoint, 5,5);
//        nextPoint = drawHorizontal(finalWorldFrame, nextPoint);
//        ter.renderFrame(finalWorldFrame);
//
//        nextPoint =  drawRectangle(finalWorldFrame,  nextPoint, 5,5);
//        nextPoint = drawVertical(finalWorldFrame, nextPoint);
//        ter.renderFrame(finalWorldFrame);
//
//        nextPoint =  drawRectangle(finalWorldFrame,  nextPoint, 6,6);
//        nextPoint = drawHorizontal(finalWorldFrame, nextPoint);



        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    private static class Position{
        private int x;
        private int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void set(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static Position drawRectangle(TETile[][] word, Position p, int width, int height){
        for (int x = p.x; x < p.x + width; x += 1) {
            for (int y = p.y; y < p.y + height; y += 1) {
                if(x > p.x && x < p.x + width - 1 && y > p.y && y < p.y + height - 1)
                    word[x][y] = Tileset.FLOOR;
                else
                    word[x][y] = Tileset.WALL;
            }
        }

        return randomBorderPoint(p, width, height);
    }

    public static Position randomBorderPoint(Position p, int width, int height){
        int randomX = RANDOM.nextInt(width - 3) + 1;
        int randomY = RANDOM.nextInt(height -3) + 1;
        int dicer = RANDOM.nextInt(2);
        switch (dicer) {
            case 0: return new Position(p.x + width - 1, p.y + randomY);
            case 1: return new Position(p.x + randomX, p.y + height - 1);
            default: return new Position(p.x + width - 2, p.y + height - 2);
        }
    }
    public static Position drawVerRec(TETile[][] word, Position p){
        return drawRectangle(word, p,3,RANDOM.nextInt(10) + 3);
    }


    public static Position drawVertical(TETile[][] word, Position p){
        int Length = RANDOM.nextInt((int)Math.floor(HEIGHT / 3));
        for (int x = p.x; x <= p.x + 2; x += 1) {
            for (int y = p.y; y <= p.y + Length; y += 1) {
                if( x == p.x || x == p.x + 2)
                    word[x][y] = Tileset.WALL;
                else
                    word[x][y] = Tileset.FLOOR;
            }
        }
        return new Position(p.x, p.y + Length);
    }
    public static Position drawHorizontal(TETile[][] word, Position p){
        int Length = RANDOM.nextInt((int)Math.floor(WIDTH / 3));
        for (int x = p.x; x <= p.x + Length; x += 1) {
            for (int y = p.y; y <= p.y + 2; y += 1) {
                if( y == p.y || y == p.y + 2)
                    word[x][y] = Tileset.WALL;
                else
                    word[x][y] = Tileset.FLOOR;
            }
        }
        return new Position(p.x + Length, p.y);
    }

    public static void drawCorner(TETile[][] word, Position p){
        int Length = RANDOM.nextInt((int)Math.floor(HEIGHT / 2));
        for (int x = p.x; x <= p.x + 2; x += 1) {
            for (int y = p.y; y <= p.y + Length; y += 1) {
                if( x == p.x || x == p.x + 2)
                    word[x][y] = Tileset.WALL;
                else
                    word[x][y] = Tileset.FLOOR;
            }
        }
    }

    public static void main(String[] args){
        MapGenerator mg = new MapGenerator(1233445);
        mg.generate();
    }
}
