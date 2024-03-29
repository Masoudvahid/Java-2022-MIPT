import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTester {
    @Test
    void task1(){
        Tasks<String> stringTask = new Tasks<>();
        assertEquals(stringTask.task1(Stream.of("a", "a", "b"), "a"), 2);
        assertEquals(stringTask.task1(Stream.of("a", "a", "b"), "c"), 0);

        Tasks<Integer> intTask = new Tasks<>();
        assertEquals(intTask.task1(Stream.of(1, 2, 3, 2, 2), 2), 3);
        assertEquals(intTask.task1(Stream.of(1, 2, 3), 4), 0);
    }

    @Test
    void task2(){
        Tasks<Double> doubleTask = new Tasks<>();
        assertEquals(doubleTask.task2(Arrays.asList(1D, 2D, 3D, 15D)), 15);
        assertNull(doubleTask.task2(Collections.emptyList()));
    }

    @Test
    void task3(){
        Tasks<Integer> intTask = new Tasks<>();
        assertEquals(intTask.task3(Stream.of(1, 2, 3, 15)), Arrays.asList(2, 3));
        assertEquals(intTask.task3(Stream.of(12, 4, 7, 15, 123, 12, 6, 345)), Arrays.asList(4, 7));
    }

    @Test
    void task4(){
        Tasks<Integer> intTask = new Tasks<>();
        assertTrue(intTask.task4(Stream.of(1, 2, 3, 4), 4));
        assertFalse(intTask.task4(Stream.of(1, 2, 3, 4), 5));
    }

    @Test
    void task5(){
        Tasks<String> stringTasks = new Tasks<>();
        assertEquals(stringTasks.task5(Stream.of("abc", "abc", "a", "aa")), Arrays.asList("a", "aa", "abc"));
        Tasks<Integer> intTasks = new Tasks<>();
        assertEquals(intTasks.task5(Stream.of(3, 2, 4, 2)), Arrays.asList(2, 3, 4));
        assertEquals(stringTasks.task5(Stream.of()), Collections.emptyList());
    }
}