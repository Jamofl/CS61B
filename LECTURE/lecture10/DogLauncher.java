import java.util.Comparator;

public class DogLauncher {
    public static void main(String[] args){

        // use comparable
        Dog[] dogs = {new Dog("jam", 10), new Dog("tom", 15), new Dog("yin", 7)};
        Dog maxdog = (Dog) Maximizer.max(dogs);
        maxdog.bark();

        // use comparator
        Dog d1 = new Dog("am", 20);
        Dog d2 = new Dog("bom", 15);

        Comparator<Dog> nc = Dog.getNameComparator(); // create a comparator
        if (nc.compare(d1,d2) > 0)
            d1.bark();
        else
            d2.bark();

        Comparator<Dog> sc = Dog.getSizeComparator();
        if (sc.compare(d1, d2) > 0)
            d1.bark();
        else
            d2.bark();
    }
}
