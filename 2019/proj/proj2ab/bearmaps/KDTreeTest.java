package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void correctnessTest(){
        Random random = new Random(1000);
        List<Point> points = new ArrayList<>();
        int query = 10000;
        int numOfPoints = 10000;
        int bound = 10000;
        for(int i = 0 ; i < numOfPoints; i ++){
            double x = random.nextDouble() * bound;
            double y = random.nextDouble() * bound;
            points.add(new Point(x, y));
        }

        KDTree kd = new KDTree(points);
        Point p1;

        NaivePointSet nn = new NaivePointSet(points);
        Point p2;

        for(int i = 0; i < query; i ++){
            double x = random.nextInt(bound);
            double y = random.nextInt(bound);
            p1 = kd.nearest(x, y);
            p2 = nn.nearest(x, y);
            assertEquals(p1.getX(), p2.getX(), 0.001);
            assertEquals(p1.getY(), p2.getY(), 0.001);

        }
    }

    @Test
    public void TimeTest(){
        Random random = new Random(1000);
        List<Point> points = new ArrayList<>();
        int query = 100000;
        int numOfPoints = 10000;
        int bound = 10000;
        for(int i = 0 ; i < numOfPoints; i ++){
            double x = random.nextDouble() * bound;
            double y = random.nextDouble() * bound;
            points.add(new Point(x, y));
        }

        Stopwatch sw1 = new Stopwatch();
        KDTree kd = new KDTree(points);
        System.out.println("KD TREE finished constructed, using " + sw1.elapsedTime());


        Stopwatch sw4 = new Stopwatch();
        NaivePointSet nn = new NaivePointSet(points);
        System.out.println("NaiveSet finished constructed, using " + sw4.elapsedTime());

        Point p1;
        Point p2;

        Stopwatch sw3 = new Stopwatch();
        for(int i = 0; i < query; i ++){
            double x = random.nextInt(bound);
            double y = random.nextInt(bound);
            p1 = kd.nearest(x, y);
        }
        System.out.println("KD TREE finished searching, using " + sw3.elapsedTime());

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < query; i ++){
            double x = random.nextInt(bound);
            double y = random.nextInt(bound);
            p2 = nn.nearest(x, y);
        }
        System.out.println("NaiveSet finished searching, using " + sw2.elapsedTime());

    }

}
