package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void testenlargeArrayImage1() {
        String[][] actual = {
                {"*", "*"},
                {"*", " "},
                {"*", " "},
                {"*", "*"},
        };

        String[][] expected = {
                {"*", "*","*", "*"},
                {"*", "*","*", "*"},
                {"*", "*", " ", " "},
                {"*", "*", " ", " "},
                {"*", "*", " ", " "},
                {"*", "*", " ", " "},
                {"*", "*","*", "*"},
                {"*", "*","*", "*"},
        };
        String[][] enlargedImage = App.enlargeArrayImage(actual);
        assertThat(enlargedImage).isEqualTo(expected);
    }
    @Test
    void testenlargeArrayImage2() {
        String[][] actual = {
                {"*"},
                {"*"},

        };

        String[][] expected = {
                {"*", "*"},
                {"*", "*"},
                {"*", "*"},
                {"*", "*"},
        };
        String[][] enlargedImage = App.enlargeArrayImage(actual);
        assertThat(enlargedImage).isEqualTo(expected);
    }
    @Test
    void testenlargeArrayImage3() {
        String[][] actual = {
                {}
        };

        String[][] expected = {
                {},{},
        };
        String[][] enlargedImage = App.enlargeArrayImage(actual);
        assertThat(enlargedImage).isEqualTo(expected);
    }
}
// END
