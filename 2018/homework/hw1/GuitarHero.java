import java.util.ConcurrentModificationException;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {
        /* create two guitar strings, for concert 0 ~ 36 */

        synthesizer.GuitarString[] strings = new synthesizer.GuitarString[37];
        for(int i = 0; i < 37; i ++){
            double CONCERT = CONCERT_A * Math.pow(2, (i-24)/12);
            strings[i] = new synthesizer.GuitarString(CONCERT);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                System.out.println(key + " :  "+ index);
                if ( index >= 0) {
                    strings[index].pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = 0.0;
            for(synthesizer.GuitarString s : strings){
                sample += s.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(synthesizer.GuitarString s : strings){
                s.tic();
            }
        }
    }
}

