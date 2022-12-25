package DateTime;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoField;

public class Java8Timestamp {
    public static void main(String[] args) {
        // Current timestamp
        Instant timestamp = Instant.now();
        System.out.println("Current timestamp : " + timestamp);
//        int i = timestamp.get(ChronoField.YEAR);

//        System.out.println(year.toString());

        // Example of Duration
        Duration sixtyDay = Duration.ofNanos(100);
        System.out.println(sixtyDay.getSeconds());
    }
}
