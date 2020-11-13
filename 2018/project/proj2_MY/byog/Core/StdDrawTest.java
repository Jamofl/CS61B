package byog.Core;

import edu.princeton.cs.algs4.StdDraw;

public class StdDrawTest {
        public static void main(String[] args) {
            StdDraw.setPenRadius(0.05);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(1, 0);
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.line(0.2, 0.2, 0.8, 0.2);
        }
}
