package bearmaps.hw4;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    public Stopwatch sw;
    public ExtrinsicMinPQ pq;
    public AStarGraph input;
    public Map<Vertex, Double> distTo;
    public Map<Vertex, Vertex> edgeTo;
    private SolverOutcome outcome;
    private double timeout;
    private int numOfExplore;
    private Vertex start;
    private Vertex end;
    double timespan;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        this.start = start;
        this.end = end;
        this.input = input;
        this.timeout = timeout;

        this.sw = new Stopwatch();
        this.pq = new ArrayHeapMinPQ();
//        this.pq = new DoubleMapPQ();
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.timespan = 0;
        this.numOfExplore = 0;

        AStar();
    }

    public void AStar(){
        pq.add(start, 0 + input.estimatedDistanceToGoal(start, end)); // add first node to the priority queue
        distTo.put(start, 0.0); // distance to start is 0.0
        edgeTo.put(start, start);  // Vertex to start is start itself

        Vertex p;
        Vertex q;
        double weight;
        while (true){
            if(pq.size() != 0 && pq.getSmallest().equals(end)){
                outcome = SolverOutcome.SOLVED;
                return;
            }
            else if(pq.size() == 0){
                outcome = SolverOutcome.UNSOLVABLE;
                return;
            }
            else if(timespan >= timeout){
                outcome = SolverOutcome.TIMEOUT;
                return;
            }

            numOfExplore += 1;
            Vertex poppedVertex = (Vertex) pq.removeSmallest();

            //relax all edges outgoing from vertex popped
            for( WeightedEdge<Vertex> edge : (List<WeightedEdge<Vertex>>) this.input.neighbors(poppedVertex)){
                p = edge.from();
                q = edge.to();
                weight = edge.weight();
                if ((distTo.get(q) == null) || (distTo.get(p) + weight < distTo.get(q))){
                    distTo.put(q, distTo.get(p) + weight);
                    edgeTo.put(q, p);
                    double h = input.estimatedDistanceToGoal(q, end);
                    if (!pq.contains(q))
                        pq.add(q, distTo.get(q) + h);
                    else
                        pq.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                }
            }
            timespan = sw.elapsedTime();
        }

    }

    public SolverOutcome outcome(){
        return outcome;
    }

    //A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.
    public List<Vertex> solution(){
        if(outcome().equals(SolverOutcome.TIMEOUT) || outcome().equals(SolverOutcome.UNSOLVABLE))
            return new LinkedList<>();
        else{
            Vertex from = edgeTo.get(end);
            LinkedList<Vertex> answer = new LinkedList<>();
            answer.addFirst(end);

            if(from.equals(end)){
                return answer;
            }
            else{
                while(!from.equals(start)){
                    answer.addFirst(from);
                    from = edgeTo.get(from);
                }
                answer.addFirst(start);
                return answer;
            }
        }
    }

    //The total weight of the given solution, taking into account edge weights. Should be 0 if result was TIMEOUT or UNSOLVABLE.
    public double solutionWeight(){
        if(outcome().equals(SolverOutcome.TIMEOUT) || outcome().equals(SolverOutcome.UNSOLVABLE))
            return 0;
        else{
            return distTo.get(end);
        }
    }

    //The total number of priority queue dequeue operations.
    public int numStatesExplored(){
        return this.numOfExplore;
    }

    //The total time spent in seconds by the constructor.
    public double explorationTime(){
        return this.timespan;
    }
}
