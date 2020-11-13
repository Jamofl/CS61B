package bearmaps;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KDTree {

    private class Node implements Comparable<Node>{
        public Node left;
        public Node right;
        public Point point;
        public boolean flag; // level flag, specify x or y coordinate

        public Node(Point p, Node left, Node right){
            this.point = p;
            this.left = left;
            this.right = right;
            this.flag = false;
        }

        @Override
        public int compareTo(Node node) {
            if (this.flag == false){
                return (this.point.getX() - node.point.getX()) >= 0 ? 1 : -1;
            }
            else{
                return (this.point.getY() - node.point.getY()) >= 0 ? 1 : -1;
            }
        }
        public double simpleDist(Node n){
            if (this.flag == false){
                return Math.pow(this.point.getX() - n.point.getX(), 2);
            }
            else{
                return Math.pow(this.point.getY() - n.point.getY(), 2);
            }
        }

        public void changeFlag() {
            this.flag = !this.flag;
        }

        public void add(Node n) {
            if (n == null)
                return;
            n.changeFlag();
            if (this.compareTo(n) <= 0) {
                if (this.right == null)
                    this.right = n;
                else
                    this.right.add(n);
            }
            else {
                if (this.left == null)
                    this.left = n;
                else
                    this.left.add(n);
            }
        }
    }

    private Node root;

    public KDTree(List<Point> points){
        root = new Node(points.get(0), null, null);
        for(int i = 1; i < points.size(); i ++){
            Node temp = new Node(points.get(i),null,null);
            root.add(temp);
        }
    }

    public Point nearest(double x, double y){
        return nearest(root, new Node(new Point(x, y), null, null),
                new Node(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), null, null),Integer.MAX_VALUE).point;
    }

    private Node nearest(Node n, Node goal, Node best, double bestDist){
        if (n == null)
            return best;
        bestDist = Point.distance(best.point, goal.point);
        double temp = Point.distance(n.point, goal.point);
        if ( temp < bestDist){
            best = n;
            bestDist = temp;
        }
        Node goodSide;
        Node badSide;
        if (n.compareTo(goal) <= 0){
            goodSide = n.right;
            badSide = n.left;
        }
        else {
            goodSide = n.left;
            badSide = n.right;
        }
        best = nearest(goodSide, goal, best, bestDist);
        if(n.simpleDist(goal) < bestDist)
            best = nearest(badSide, goal, best, bestDist);

        return best;
    }

    public static void main(String[] args){
        List<Point> lst = new ArrayList<>();
        lst.add(new Point(2,3));
        lst.add(new Point(1,1));
        lst.add(new Point(4,4));
        lst.add(new Point(3,5));
        lst.add(new Point(5,6));
        lst.add(new Point(6,8));
        lst.add(new Point(7,11));
        lst.add(new Point(8,13));
        lst.add(new Point(9,15));

        KDTree kd = new KDTree(lst);
        Point p = kd.nearest(9,11);
        System.out.println(p.getX() + ",  " + p.getY());

        NaivePointSet nn = new NaivePointSet(lst);
        Point ret = nn.nearest(9, 11); // returns p2
        System.out.println(ret.getX() + ",  " + ret.getY());
        System.out.println(Math.pow(2.3, 5));
    }
}
