public class Horse {
    Horse same;
    String name;

    public Horse(String lee) {
        name = lee;
    }

    public Horse same(Horse horse) {
        if (same != null) {
        Horse same = horse;
        same.same = horse;
        same = horse.same;
        }
        return same.same;
        }

public static void main(String[] args) {
    Horse horse = new Horse("youve been");
    Horse cult = new Horse("horsed");
    cult.same = cult;
    cult = cult.same(horse);
    System.out.println(cult.name);
    System.out.println(horse.name);
    }
}