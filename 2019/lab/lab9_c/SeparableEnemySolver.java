import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeparableEnemySolver {

    Graph g;

    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        // TODO: Fix me
        // 整体思路为，遍历整个图，对于图中的每一个节点s，找出与之直接相连的所有节点t1, t2，则s和ti..即为需要放在
        // 两个不同集合中的节点；在判断的部分，将这些列表中的点按照有无交集的规则依次加入集合S和集合T中,若最终集合无重复元素,则说明可以分割,否则不能
        // 获取集合列表
        Set<String> labels = g.labels();
        int numOfNodes = labels.size();
        Set<String>[] sets1 = new Set[numOfNodes];
        Set<String>[] sets2 = new Set[numOfNodes];
        for(int i = 0 ; i < numOfNodes; i ++){ //O(V)
            sets1[i] = new HashSet<>();
            sets2[i] = new HashSet<>();
        }
        int count = 0;
        for(String label : labels){ //O(V + E)
            sets1[count].add(label);
            for(String nei : g.neighbors(label))
                sets2[count].add(nei);
            count ++;
        }

        //判断
        Set<String> S = new HashSet<>();
        Set<String> T = new HashSet<>();
        for(int i = 0 ; i < numOfNodes ; i ++){ // O(V + E)
            if(!Collections.disjoint(S, sets1[i])) //如果和S有交集
            {
                S.addAll(sets1[i]);
                T.addAll(sets2[i]);
            }
            else if(!Collections.disjoint(T, sets1[i])) //如果和T有交集
            {
                T.addAll(sets1[i]);
                S.addAll(sets2[i]);
            }
            else{ //如果和 S / T 都没有交集，则随机添加到S和T中
                S.addAll(sets1[i]);
                T.addAll(sets2[i]);
            }
        }
        return S.size() + T.size() == numOfNodes;
    }

    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */

}
