import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppExampleTest {

    @Test
    void exampleTest() {
        // Step 1. Initial env
//        String a = "Hello, world!";
        String b = "Hello, worl!";

        // Step 2. Run some functionality
//        String s = App.getString(a);

        // Step 3. Compare expected and actual results
        String a = "Hello, world!";
        assertThat(a)
                .startsWith("Hello")
                .contains("llo, ")
                .endsWith("!");
    }
}