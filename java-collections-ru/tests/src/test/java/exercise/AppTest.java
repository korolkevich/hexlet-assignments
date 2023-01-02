package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> expected = new ArrayList<>();
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        List<Integer> actual = App.take(numbers1, 0);
        assertThat(actual).isEqualTo(expected);

        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2));

        List<Integer> actual1 = App.take(numbers1, 2);
        assertThat(actual1).isEqualTo(expected1);
        // END
    }
}
