import java.util.*;

public class Main {
    public static void main(String[] args) {
        Crane kraan1 = new Crane(4, 7, 500, 500, new Coordinate(14, 50));
        Crane kraan2 = new Crane(6, 10, 500, 500, new Coordinate(420, 210));

        List<Crane> kranen = new ArrayList<Crane>();
        kranen.add(kraan1);
        kranen.add(kraan2);

        System.out.println(kraan1);
        System.out.println(kraan2);

        kraan1.move(new Coordinate(78, 140), kranen);
    }
}