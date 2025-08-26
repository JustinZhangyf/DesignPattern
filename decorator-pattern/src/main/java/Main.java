import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HistorySet<String> strSet = new HistorySet<>(new HashSet<>());

        strSet.add("James");
        strSet.add("Tom");
        strSet.add("Alice");
        strSet.add("Bob");

        strSet.remove("Tom");
        strSet.remove("Justin");
        strSet.remove("Alice");

        System.out.println(strSet);

        Set<String> synchronizedSet = Collections.synchronizedSet(strSet);

        // 下方代码块并不是线程安全的，因为isEmpty和add两个操作不是原子操作
        if (synchronizedSet.isEmpty()) {
            synchronizedSet.add("Tommy");
        }
    }
}
