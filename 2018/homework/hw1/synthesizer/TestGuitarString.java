package synthesizer;

/* Since this test is part of a package, we have to import the package version of StdAudio. */
/* Don't worry too much about this, we'll get there in due time. */
import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/** Tests the GuitarString class.
 *  @author Josh Hug
 */

public class TestGuitarString {
    @Test
    public void testPluck(){
        Map<Integer, Double> map = new HashMap<>()
        {
            {   put(1, 523.0);
                put(2, 587.0);
                put(3, 659.0);
                put(4, 698.0);
                put(5, 784.0);
                put(6, 880.0);
                put(7, 988.0);
                put(11, 1046.0);
                put(22, 1175.0);
                put(33, 1318.0);
                put(44, 1397.0);
                put(55, 1568.0);
                put(66, 1760.0);
                put(77, 1967.0);
            }
        };
        int[] pitches = {11,22,6,11,7,11,22,11,11,22,6,11,7,11,22,33};
        double[] spans = {0.5,0.5,1,1,0.5,0.5,0.5,3.5, 0.5,0.5,1,1,0.5,0.5,0.5,3.5};
        for(int j = 0; j < pitches.length; j ++){
            GuitarString xString = new GuitarString(map.get(pitches[j]));
            xString.pluck();
            for (int i = 0; i < 30000 * spans[j]; i += 1) {
                StdAudio.play(xString.sample());
                xString.tic();
            }
        }
    }


   @Test
    public void testPluckTheAString() {
        double CONCERT_A = 523.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }


    @Test
    public void testTic() {
        // Create a GuitarString of frequency 11025, which
        // is an ArrayRingBuffer of length 4. 
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // Record the front four values, ticcing as we go.
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic(); 
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // Check that new sample is correct, using tolerance of 0.001.
        // See JUnit documentation for a description of how tolerances work
        // for assertEquals(double, double)
        assertEquals(expected, s5, 0.001);

    }


    /** Calls tests for GuitarString. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestGuitarString.class);
    }
} 
