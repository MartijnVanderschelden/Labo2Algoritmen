import java.util.Comparator;

public class sortByLength implements Comparator<Container> {
    @Override
    public int compare(Container o1, Container o2) {
        return o1.length - o2.length;
    }
}
