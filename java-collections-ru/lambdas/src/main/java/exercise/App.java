package exercise;


import java.util.Arrays;


// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] inputArray) {
        int xLength = inputArray[0].length;
        int yLength = inputArray.length;

        String[][] newArray = new String[yLength * 2][xLength * 2];
        int yIndex = 0;
        for (int y = 0; y < yLength; y++) {
            String[] newArrayRow = new String[xLength * 2];
            int xIndex = 0;

            for (int x = 0; x < xLength; x++) {
                newArrayRow[xIndex] = inputArray[y][x];
                newArrayRow[xIndex + 1] = inputArray[y][x];
                xIndex += 2;
            }

            newArray[yIndex] = newArrayRow;
            newArray[yIndex + 1] = newArrayRow;
            yIndex += 2;
        }

        return newArray;
    }
}
// END
