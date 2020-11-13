package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private static final Random RANDOM = new Random(1245);

    // add a hexagon at position (x, y) with length of len in the TETile word with TETile(symbol) T
    public static void addHexagon(TETile[][] world, int len, int x, int y, TETile T){
        // len = 3 rows = 6
        drawFront(world, len, x,y + len + 1, T);
        drawMiddle(world, len, x, y + len - 1, T);
        drawBack(world, len, x, y, T);

    }

    // calculate the maximum length(a.k.a the middle length) of a hexagon with size of len
    private static  int calMaxLen(int len){
        int lenMiddle = len;
        for(int i = 1; i < len; i++){
            lenMiddle += 2;
        }
        return lenMiddle;
    }

    // draw upper part of a hexagon
    public static void drawFront(TETile[][] world, int len, int x, int y, TETile T){
        // x = 0, y = 4;
        for(int i = 1; i < len; i ++){
            for(int j = 1; j <= calMaxLen(len) - i; j ++){
                if(j > i)
                    world[j + x][i + y] = T;
            }
        }
    }

    // draw middle part of a hexagon
    public static void drawMiddle(TETile[][] world, int len,int x, int y, TETile T){
        for(int i = 1; i <= 2; i ++){
            for(int j = 1; j <= calMaxLen(len); j ++){
                world[j + x][i + y] = T;
            }
        }

    }

    // draw lower part of a hexagon
    public static void drawBack(TETile[][] world, int len, int x, int y, TETile T){
        for(int i = 1; i < len; i ++){
            for(int j = 1; j <= calMaxLen(len) - (len - i); j ++){
                if(j > len - i)
                    world[j + x][i + y] = T;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(9);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.GRASS;
            case 6: return Tileset.PLAYER;
            case 7: return Tileset.SAND;
            case 8: return Tileset.TREE;
            case 9: return Tileset.UNLOCKED_DOOR;
            default: return Tileset.NOTHING;
        }
    }

    public static void drawFour(TETile[][] world, int x, int y){
        addHexagon(world, 3, x, y, randomTile());
        addHexagon(world, 3,x + 5,y + 3,randomTile());
        addHexagon(world, 3,x + 5,y - 3,randomTile());
        addHexagon(world, 3,x + 10, y,randomTile());
    }

    public static void drawNine(TETile[][] world, int x, int y){
        drawFour(world, x,y);
        drawFour(world, x + 5,y + 3);
        drawFour(world, x + 5,y - 3);
        drawFour(world, x + 10, y);
    }


    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // draw
        drawNine(world, 5,5);
        drawNine(world, 5,11);
        drawNine(world, 5,17);

        ter.renderFrame(world);

    }
}
