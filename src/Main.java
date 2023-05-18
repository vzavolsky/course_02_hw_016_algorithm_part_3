import java.util.Random;

public class Main {
    public static void main(String[] args) {
        IntegerList arr = new ArrayList();

        System.out.println(arr.size());
        arr.add(5);
        System.out.println(arr.size());
        arr.add(1);
        System.out.println(arr.size());
        arr.add(6);
        System.out.println(arr.size());
        arr.add(7);
        System.out.println(arr.size());
        arr.add(10);
        arr.add(4);
        arr.add(5);
        arr.add(44);

        System.out.println(arr.toString(", "));

        arr.sortInsertion();
        System.out.println(arr.toString(", "));
        System.out.println(arr.containsBinary(5));
        System.out.println(arr.containsBinary(51));
    }
}