package DateTime;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class Java8Date {

    public static void main(String[] args) {

        // JDBC - java DB connection
//        Date date = Date.UTC(2000,1,1,1,1,1);   // Mutable

        // Get current date
        LocalDate today = LocalDate.now();
        System.out.println("Current date : " + today);

        // Create LocalDate
        LocalDate specificDate = LocalDate.of(2020, 11, 30);
        System.out.println("Date : " + specificDate);

        // Print 256th day of 2020
        LocalDate day256 = LocalDate.ofYearDay(2020, 256);
        System.out.println("256th day of 2020 : " + day256);
    }
}
