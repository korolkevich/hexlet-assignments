package exercise;

// BEGIN

import java.util.Arrays;

class ReversedSequence implements CharSequence {
    private char[] reversedSequence;
    private char[] rawSequence;

    ReversedSequence(String text) {
        this.rawSequence = text.toCharArray();
        this.reversedSequence = reverse(this.rawSequence);
    }

    private static char[] reverse(char[] arrText) {
        int textLength = arrText.length;
        char[] reversedArrText = new char[textLength];
        int cnt = 0;
        for (int i = 0; i < textLength; i++) {
            reversedArrText[cnt] = arrText[textLength - i - 1];
            cnt += 1;
        }
        return reversedArrText;
    }

    public String toString() {
        return String.valueOf(this.reversedSequence);
    }

    public char charAt(int charNum) {
        if (charNum < this.reversedSequence.length) {
            return this.reversedSequence[charNum];
        }
        return Character.MIN_VALUE;
    }

    public int length() {
        return this.reversedSequence.length;
    }

    public ReversedSequence subSequence(int start, int end) {
        int textLength = this.reversedSequence.length;

        if (start > end) {
            return new ReversedSequence("");
        } else if (start > textLength) {
            return new ReversedSequence("");
        }

        char[] arrText = new char[end - start];

        int cnt = 0;
        for (int i = 0; i < textLength; i++) {
            if (i >= start && i < end ) {
                arrText[cnt] = this.reversedSequence[i];
                cnt += 1;
            }
        }

        return new ReversedSequence(String.valueOf(reverse(arrText)));
    }
}
// END
