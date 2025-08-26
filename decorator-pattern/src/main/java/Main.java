import java.util.HashSet;

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
    }
}
