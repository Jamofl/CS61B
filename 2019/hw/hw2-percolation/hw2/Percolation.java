package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    public int[] bottomNode; // bottom virtual node
    //public int bottomNodeDry; // dry bottom virtual node
    public int topNode; // top virtual node
    public int openCount;
    private boolean isPercolated;
    public int N;
    public boolean[][] grid;
    public WeightedQuickUnionUF uf;
    private int XYto1D(int x, int y){
        return x * N + y;
    }
    public int last_row;
    public int last_col;
    private int countBottomNode;

    private void validate(int x, int y){
        if ((x > N - 1) || (x < 0) || (y > N - 1) || (y < 0))
            throw new IndexOutOfBoundsException();
    }
    public Percolation(int N)// create N-by-N grid, with all sites initially blocked
    {
        if (N < 0)
            throw new IllegalArgumentException();

        this.N = N;
        this.isPercolated = false;
        this.openCount = 0;
        this.countBottomNode = 0;
        this.bottomNode = new int[N]; // bottom virtual node
        this.topNode = XYto1D(N-1, N-1) + 1; // top virtual node
        this.uf = new WeightedQuickUnionUF(N * N + N + 1);
        this.grid = new boolean[N][N]; // O(n2)
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                this.grid[row][col] = false;
            }
        }
        for (int i = 0; i < N; i++){ // O(n)
            bottomNode[i] = XYto1D(N-1, N-1) + 2 + i;
        }
    }
    private int createBottomNode(){
        //this.bottomNode[countBottomNode]= XYto1D(N-1, N-1) + 2 + countBottomNode; // bottom virtual node dry
        int bottomnode =  this.bottomNode[countBottomNode];
        this.countBottomNode++;
        return bottomnode;

    }

    private void updateAround(int row, int col){
        int[] cols = {0, -1, 1, 0};
        int[] rows = {-1, 0, 0, 1};
        for(int i = 0; i < 4; i ++){
            int tempRow = row + rows[i];
            int tempCol = col + cols[i];
            if ((tempCol > N - 1) || (tempCol < 0) || (tempRow > N - 1) || (tempRow < 0))
                continue;
            if (grid[tempRow][tempCol]){
                uf.union(XYto1D(row, col), XYto1D(tempRow, tempCol));
            }
        }
    }

    public void open(int row, int col)   // open the site (row, col) if it is not open already
    {
        validate(row, col);
//        System.out.println("open clicked : " + row + " , "  + col);
        if (last_row == row && last_col == col)// if same block is clicked twice continuously
            return;

        if(isOpen(row, col))// if this block is already opened
            return;
        else {
            grid[row][col] = true;
            openCount++;
            if (row == 0) // if grid opened is in the first row
                uf.union(topNode, XYto1D(row, col));
            else if (row == N - 1) // if grid opened is in the last row
                uf.union(createBottomNode(), XYto1D(row, col));

            updateAround(row, col); // check 4 neighbors around and union them if necessary
//            System.out.println("top node parent " + uf.find(topNode));
//            System.out.println("bottom node1 parent " + uf.find(bottomNode[0]));
//            System.out.println("bottom node2 parent " + uf.find(bottomNode[1]));
//
//            System.out.println("x node parent " + uf.find(XYto1D(row, col)));
//            System.out.println(isPercolated);
//            System.out.println();
            last_row = row;
            last_col = col;
        }
    }
    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        validate(row, col);
        return grid[row][col];
    }
    public boolean isFull(int row, int col)  // is the site (row, col) full?
    {
        validate(row, col);
        return uf.connected(topNode, XYto1D(row, col));
    }
    public int numberOfOpenSites()           // number of open sites
    {
        return openCount;
    }
    public boolean percolates()              // does the system percolate?
    {
        for(int i = 0; i < countBottomNode; i ++){
            if (uf.connected(topNode, bottomNode[i])){
                this.isPercolated = true;
                break;
            }
        }
        return this.isPercolated;
    }
    public static void main(String[] args)   // use for unit testing (not required, but keep this here for the autograder)
    {
        Percolation p = new Percolation(4);
        p.open(2,3);
        p.open(1,0);
        p.open(3,0);
        p.open(2,0);

    }
}
