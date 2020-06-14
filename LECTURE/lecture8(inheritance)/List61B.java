// a interface showing that what list can do but not how to do that
// interface the list of all method signatures
// subclass MUST override all of these methods!!!
public interface List61B<Item> {
        public void insert(Item x, int position);
        public void addFirst(Item x);
        public void addLast(Item x);
        public Item getFirst();
        public Item getLast();
        public Item get(int i);
        public int size();
        public Item removeLast();
        default public void print() { // default keyword shows it is a implementation inheritance
            for (int i = 0; i < size(); i += 1) {
                System.out.print(get(i) + " ");
            }
        }
}
