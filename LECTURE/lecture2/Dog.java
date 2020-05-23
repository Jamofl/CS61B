public class Dog {
    public static String owner = "jam";//static variable, for the whole class
    public int weight;// non static variable, for instance
    public Dog(int w){ //constructor like"__init__"in Python
        weight = w;
    }
    public void makeNoise(){//non static method, for instance to use
        if (weight < 10)
            System.out.println("small dog bark!");
        else if (weight < 20)
            System.out.println("medium dog bark!");
        else
            System.out.println("big dog bark!");
    }
    public static Dog biggerDog(Dog d1, Dog d2){//static method, for class to use
        if (d1.weight > d2.weight)
            return d1;
        else
            return d2;
    }

    public Dog maxDog(Dog d2){
        if (this.weight > d2.weight)
            return this;
        else
            return d2;
    }
}