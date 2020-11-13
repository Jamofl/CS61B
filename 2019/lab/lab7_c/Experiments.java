import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.*;

/**
 * Created by hug.
 */
public class Experiments {
    public static void plotDoubleY(List X, List Y1, List Y2){
        Random r = new Random();
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random bst depth", X, Y1);
        chart.addSeries("optimal bst depth", X, Y2);
        new SwingWrapper(chart).displayChart();
    }
    public static void plotSingleY(List X, List Y1, String info){
        Random r = new Random();
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random bst depth " + info, X, Y1);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment1() {
        List<Double> normalDepth = new ArrayList<>();
        List<Double> optDepth = new ArrayList<>();
        List<Integer> X = new ArrayList<>();

        BST<Integer> bst = new BST<>();
        Random rand = new Random();
        int randomNum;
        double depth;

        //get unique random number
        List<Integer> randoms = new ArrayList<>();
        for(int i = 0; i < 10000; i ++){
            randoms.add(i);
        }
        Collections.shuffle(randoms);

        for(int i = 0; i < 10000; i ++){
            bst.add(randoms.get(i));
            depth = bst.getAveDepth();

            X.add(i);
            normalDepth.add(depth);
            optDepth.add(ExperimentHelper.optimalAverageDepth(i + 1));
        }
        plotDoubleY(X, normalDepth, optDepth);

    }

    public static void experiment2() {
        List<Integer> X = new ArrayList<>();
        List<Double> Y = new ArrayList<>();

        BST<Integer> bst = new BST<>();
        List<Integer> randoms = new ArrayList<>();
        for(int i = 0; i < 5000; i ++){
            randoms.add(i);
        }
        Collections.shuffle(randoms);

        for(int i = 0; i < 5000; i++) {
            bst.add(randoms.get(i));
        }

        //initialize
        X.add(0);
        Y.add(bst.getAveDepth());
        // conduct random insertion and deletion
        List<Integer> randoms2 = new ArrayList<>();
        for(int i = 0; i < 300000; i ++){
            randoms2.add(i);
        }
        Collections.shuffle(randoms2);

        for(int i = 0; i < 300000; i ++){
            ExperimentHelper.successorDelete(bst);
            bst.add(randoms2.get(i));

            X.add(i + 1);
            Y.add(bst.getAveDepth());
        }
        plotSingleY(X, Y, "exp2");


    }

    public static void experiment3() {
        List<Integer> X = new ArrayList<>();
        List<Double> Y = new ArrayList<>();

        BST<Integer> bst = new BST<>();
        List<Integer> randoms = new ArrayList<>();
        for(int i = 0; i < 5000; i ++){
            randoms.add(i);
        }
        Collections.shuffle(randoms);

        for(int i = 0; i < 5000; i++) {
            bst.add(randoms.get(i));
        }

        //initialize
        X.add(0);
        Y.add(bst.getAveDepth());
        // conduct random insertion and deletion
        List<Integer> randoms2 = new ArrayList<>();
        for(int i = 0; i < 300000; i ++){
            randoms2.add(i);
        }
        Collections.shuffle(randoms2);

        for(int i = 0; i < 300000; i ++){
            ExperimentHelper.randomDelete(bst);
            bst.add(randoms2.get(i));

            X.add(i + 1);
            Y.add(bst.getAveDepth());
        }
        plotSingleY(X, Y, "exp3");
    }

    public static void main(String[] args) {
        experiment2();
        experiment3();

    }
}
