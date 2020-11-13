package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

import java.util.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        int r; int g; int b;
        for (int i = 0; i < 10000; i ++){
            r = StdRandom.uniform(0, 51) * 5;
            g = StdRandom.uniform(0, 51) * 5;
            b = StdRandom.uniform(0, 51) * 5;
            if (r == g || r == b || g == b)
                continue;
            SimpleOomage[] oo = new SimpleOomage[6];
            oo[0] = new SimpleOomage(r, g, b);
            oo[1] = new SimpleOomage(r, b, g);
            oo[2] = new SimpleOomage(g, b, r);
            oo[3] = new SimpleOomage(g, r, b);
            oo[4] = new SimpleOomage(b, g, r);
            oo[5] = new SimpleOomage(b, r, g);
            for(int j = 0; j < 5; j ++){
                for(int k = j + 1; k < 6; k ++){
                    int hash1 = oo[j].hashCode();
                    int hash2 = oo[k].hashCode();
                    assertNotEquals(hash1, hash2);
                }
            }
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
