package bearmaps.hw4.slidingpuzzle;


import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the Board class. The code as distributed
 * provides no useful heuristic. Implement the Hamming distance or
 * Manhattan distance if you want to see how a heuristic can dramatically
 * improve A* performance.
 * Created by hug.
 */
public class BoardGraph implements AStarGraph<Board> {
    @Override
    public List<WeightedEdge<Board>> neighbors(Board b) {
        List<Board> neighbors = b.neighbors();
        List<WeightedEdge<Board>> neighborEdges = new ArrayList<>();
        for (Board n : neighbors) {
            neighborEdges.add(new WeightedEdge<>(b, n, 1));
        }
        return neighborEdges;
    }

    @Override
    public double estimatedDistanceToGoal(Board s, Board goal) {
//        return 0;
        int maxVal = s.size() * s.size();

        int totalDistance = 0;
        for (int i = 1; i < maxVal; i += 1) {
            totalDistance += manhattanDistance(s, goal, i);
            //totalDistance += hammingDistance(s, goal);

        }
        return totalDistance;
    }

    // ???
    private int hammingDistance(Board b1, Board b2){
        int count = 0;
        for (int i = 0; i < b1.size(); i += 1) {
            for (int j = 0; j < b1.size(); j += 1) {
                if (b1.tileAt(i, j) == 0)
                    continue;
                if (b1.tileAt(i, j) != b2.tileAt(i, j)) {
                    count ++;
                }
            }
        }
        if (count >= b1.size() * b1.size())
            System.out.println("count is:  " + count);
        return count;
    }

    private int manhattanDistance(Board b1, Board b2, int n) {
        int b1row = 0;
        int b1col = 0;
        for (int i = 0; i < b1.size(); i += 1) {
            for (int j = 0; j < b1.size(); j += 1) {
                if (b1.tileAt(i, j) == n) {
                    b1row = i;
                    b1col = j;
                    break;
                }
            }
        }

        int b2row = 0;
        int b2col = 0;
        for (int i = 0; i < b2.size(); i += 1) {
            for (int j = 0; j < b2.size(); j += 1) {
                if (b2.tileAt(i, j) == n) {
                    b2row = i;
                    b2col = j;
                    break;
                }
            }
        }

        return Math.abs(b1row - b2row) + Math.abs(b1col - b2col);
    }
}
