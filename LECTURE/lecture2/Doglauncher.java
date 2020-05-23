/** a test drive class to call Dog.java */
public class Doglauncher {
    public static void main(String[] args) 
    {
        Dog d1 = new Dog(18);
        Dog d2 = new Dog(28);
        //Dog biggerdog = Dog.biggerDog(d1, d2);
        //Dog maxdog = d1.maxDog(d2);
        //maxdog.makeNoise();
        //System.out.println(Dog.owner);
        
        Dog[] manydogs = new Dog[4];
        manydogs[0] = d1;
        manydogs[1] = d2; 
        for(int i = 0; i < manydogs.length; i++ )
        {
            manydogs[i].makeNoise();
        }
    }

}