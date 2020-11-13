package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class placeRooms {
    private static final int minRoomSize = 3;
    private static final int maxRoomSize = 6;
    private static final int MAP_WIDTH = 72;
    private static final int MAP_HEIGHT = 30;
    private static final int maxRooms = 18;
    private static final int minRooms = 14;
    private static int ptr = 0;
    private static long SEED;
    private static Random RANDOM;
    public  Point player1;
    public  Point door;

    public placeRooms(long seed){
        SEED = seed;
        RANDOM = new Random(SEED);
    }

    // add only floor "."
    public void addMaze(TETile[][] world, TETile t){
        Room[] rooms = new Room[maxRooms];
        int roomNumber = minRooms + RANDOM.nextInt(maxRooms - minRooms + 1);
        for (int z = 0; z < roomNumber; z++){
            int w = minRoomSize + RANDOM.nextInt(maxRoomSize - minRoomSize + 1);
            int h = minRoomSize + RANDOM.nextInt(maxRoomSize - minRoomSize + 1);
            int x = RANDOM.nextInt(MAP_WIDTH - w - 3) + 1;
            int y = RANDOM.nextInt(MAP_HEIGHT - h - 3) + 1;
            Room newRoom = new Room(x, y, w, h);

            rooms[ptr] = newRoom;
            for (int i = rooms[ptr].x1; i <= rooms[ptr].x2; i++){
                for (int j = rooms[ptr].y1; j <= rooms[ptr].y2; j++){
                    world[i][j] = t;
                }
            }

            Point newCenter = newRoom.center;

            if (ptr != 0){
                Point prevCenter = rooms[ptr-1].center;
                Corridors corridor = new Corridors();
                if (RANDOM.nextInt(2) == 1){
                    Corridors.hCorridor hc = corridor.new hCorridor(prevCenter.x, newCenter.x, prevCenter.y);
                    Corridors.vCorridor vc = corridor.new vCorridor(prevCenter.y, newCenter.y, newCenter.x);
                }else{
                    Corridors.vCorridor vc = corridor.new vCorridor(prevCenter.y, newCenter.y, prevCenter.x);
                    Corridors.hCorridor hc = corridor.new hCorridor(prevCenter.x, newCenter.x, newCenter.y);
                }
                for (int q = 0; q < MAP_WIDTH; q++){
                    for (int a = 0; a < MAP_HEIGHT; a++){
                        if (corridor.arr[q][a] == 1){
                            world[q][a] = t;
                        }
                    }
                }
            }
            ptr++;
        }

        Room randomRoom = rooms[3];
        player1 = new Point(randomRoom.center.x, randomRoom.center.y);
        world[randomRoom.center.x][randomRoom.center.y] = Tileset.PLAYER;

        randomRoom = rooms[7];
        door = new Point(randomRoom.center.x, randomRoom.center.y);
        world[randomRoom.center.x][randomRoom.center.y] = Tileset.LOCKED_DOOR;
    }

    // add wall which surrounds floor "#"
    public void addWall(TETile[][] world, TETile t){
        for(int i = 1; i < MAP_WIDTH - 1; i ++){
            for(int j = 1; j < MAP_HEIGHT - 1 ; j ++){
                if (world[i][j] == Tileset.FLOOR)
                    surround3By3(world, new Point(i, j), t);
            }
        }
    }
    private void surround3By3(TETile[][] world, Point p, TETile t){
        for(int i = p.x - 1; i <= p.x + 1; i ++){
            for(int j = p.y - 1; j <= p.y + 1; j ++){
                if (world[i][j] == Tileset.NOTHING)
                    world[i][j] = t;
            }
        }
    }
}
