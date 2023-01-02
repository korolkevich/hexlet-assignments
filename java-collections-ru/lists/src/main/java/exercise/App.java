package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
class App {
    public static boolean scrabble(String rawChars, String word) {
        char[] lowerWord = word.toLowerCase().toCharArray();
        char[] lowerRawChars = rawChars.toLowerCase().toCharArray();

        for (char elemWord: lowerWord) {
            boolean isContainChar = checkAndRemoveElemInWord(lowerRawChars, elemWord);
            if (!isContainChar) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAndRemoveElemInWord(char[] rawChars, char elemChar) {
        for (var i = 0; i < rawChars.length; i++) {
            char elemRawChars = rawChars[i];
            if (elemRawChars == elemChar) {
                rawChars[i] = Character.MIN_VALUE;
                return true;
            }
        }
        return false;
    }
}
//END
