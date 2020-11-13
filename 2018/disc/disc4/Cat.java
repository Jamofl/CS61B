public class Cat extends Animal {
    public Cat(String name, int age){
        super(name, age);//explicitly calling super class
        //this.name = name;
        //this.age = age;
        this.noise = "Meow";
    }

    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + makeNoise());
        } 

    public static void main(String[] args){
//        Cat c = new Cat("jessy", 3);
//        c.greet();
        Animal a = (Cat)new Animal("jim", 2);
    }

    

}