import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GenericExample {
    public static void main(String[] args) {
        Manager ceo = new Manager();
        Manager cto = new Manager();
        Manager cfo = new Manager();
        Developer developer = new Developer();
//        List managers = new ArrayList<>();
        // parameterized classes
        List<Employee> employees = new ArrayList<>();

        employees.add(ceo);
        employees.add(cto);

        // bug
        employees.add(developer);

        // typecast with runtime exception
//        Manager manager = (Manager) managers.get(2);
        Employee manager = employees.get(1);
        Employee dev = employees.get(2);

        // Definition and use
        Pair<String> pair = new Pair<>("First", "Second");

        System.out.println("Point to check pair object in debug mode");

        // Generic methods
        String s = getRandomItem("A", "B", "C");

        Manager m = getRandomItem(ceo, cto, cfo);

        System.out.println("Point to check pair object in debug mode");
    }

    public static <T> T getRandomItem(T... items) {
        return items[ThreadLocalRandom.current().nextInt(items.length)];
    }

    public List<String> getString(String... arg) {
        return List.of(arg[0]);
    }
}

interface Employee {
}

class Manager implements Employee {
}

class Developer implements Employee {
}

class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}