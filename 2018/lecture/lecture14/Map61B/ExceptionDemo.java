package Map61B;

public class ExceptionDemo {
    public static void main(String[] args){
        ArrayMap<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("hello", 1);
        System.out.println(m.get("hello"));
    }
}
