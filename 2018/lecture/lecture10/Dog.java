import java.util.Comparator;
public class Dog implements Comparable<Dog>{ // Comparable is a java built in interface
    private String name;
    private int size;

    public Dog(String n, int s){
        name = n;
        size = s;
    }

    public void bark(){
        System.out.println(name + ": bark!");
    }

    // return negative number if this dog is less than the dog point at by o;
    // if you want to compare name but not size instead, you need to write a new compareTo function
    public int compareTo(Dog someDog) {
        return this.size - someDog.size;
    }

    //nested class
    private static class NameComparator implements Comparator<Dog>{
        public int compare(Dog a, Dog b){
            return a.name.compareTo(b.name);
        }
    }
    private static class SizeComparator implements Comparator<Dog>{
        public int compare(Dog a, Dog b){
            return a.size - b.size;
        }
    }

    public static Comparator<Dog> getNameComparator(){
        return new NameComparator();
    }
    public static Comparator<Dog> getSizeComparator(){
        return new SizeComparator();
    }
}

