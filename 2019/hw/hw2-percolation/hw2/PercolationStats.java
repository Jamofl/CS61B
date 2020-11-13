package hw2;
import edu.princeton.cs.introcs.StdRandom; // generate random number
import edu.princeton.cs.introcs.StdStats;// compute mean and standard deviation
import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {
    public Percolation pl;
    public int rand;
    public double[] result;
    public int T;
    private int[] toXY(int x, int N)// convert 1 dimension to 2 dimension coordinate in grid with length N
    {
        int[] toReturn = new int[2];
        toReturn[0] = x / N; // x
        toReturn[1] = x % N; // y
        return toReturn;
    }

    public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
    {
        this.T = T;
        this.result = new double[T]; // with length T
        int[] coordinate = new int[2];
        for(int i = 0; i < T; i ++){
            pl = pf.make(N);
            for(int j = 0; j < Integer.MAX_VALUE; j ++){
                rand = StdRandom.uniform(N * N);
                coordinate = toXY(rand, N);
                pl.open(coordinate[0], coordinate[1]);
                if (pl.percolates()){
                    //System.out.println("percolate !!!");
                    double temp = ((double) pl.numberOfOpenSites()/ (double)(N * N));
                    this.result[i] = temp;
                    break;
                }
            }
        }
    }
    public double mean()                                           // sample mean of percolation threshold
    {
        return StdStats.mean(this.result);
    }
    public double stddev()                                         // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(this.result);
    }
    public double confidenceLow()                                  // low endpoint of 95% confidence interval
    {
        return mean() - (1.97 * stddev()) / Math.sqrt(T);

    }
    public double confidenceHigh()                                 // high endpoint of 95% confidence interval
    {
        return mean() + (1.97 * stddev()) / Math.sqrt(T);
    }

    public static void main(String[] args){
        Stopwatch timer1 = new Stopwatch();
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(50, 3200, pf);
        System.out.println("elapsed time is : " + timer1.elapsedTime());

//        int total = 100;
//        int[] x = new int[total];
//        double[] y = new double[total];
//        for (int i = 0; i < total; i ++){
//            Stopwatch timer = new Stopwatch();
//            PercolationStats ps = new PercolationStats(1 + i, 100, pf);
//            x[i] = i;
//            y[i] = timer.elapsedTime();
//        }

    }
}
