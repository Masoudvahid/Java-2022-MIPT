import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

//import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void helloWorldTest() {
        App.main(new String[]{});
    }

    @Test
    void helloTest() {
        assertThat(2 + 2).isEqualTo(4);
    }

    @Test
    void strTest() {
        String a = "Hello, world!";
        String b = "Hello, world!";
        assertThat(a).isEqualTo(b);
    }

    @Test
    void otherStrTest() {
        String a = "Hello, world!";
        assertThat(a)
                .startsWith("Hello")
                .contains("llo, ")
                .endsWith("!");
    }

    @BeforeAll
    static void setUp() {
        System.out.println("Testing started");
    }

    @AfterAll
    static void Finalize() {
        System.out.println("Testing finished");
    }

    @BeforeEach
    void before() {
        System.out.println("This method is called before each test");
    }

    @AfterEach
    void after() {
        System.out.println("This method is called after each test");
    }

    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> {
            List<String> list = Arrays.asList("String one", "String two");
            list.get(2);
        }).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Index 2 out of bounds for length 2");
    }

    @RepeatedTest(5)
    void repeatedTest() {
        System.out.println("You will see this message five times");
    }
}