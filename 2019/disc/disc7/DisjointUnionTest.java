import org.junit.Test;
public class DisjointUnionTest {
    @Test
    public void disjointUnionTest(){
        UnionFind ds = new UnionFind(10);
        ds.union(0,1);
        ds.union(0,2);
        ds.union(3,4);
        ds.union(3,5);
        ds.union(3,0);
        int a = ds.find(3);
        int b = ds.findFast(5);
    }
}
