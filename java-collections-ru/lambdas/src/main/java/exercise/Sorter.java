package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(elem -> elem.get("gender").equals("male"))
                .sorted((elem1, elem2) -> compateAge(elem1, elem2))
                .map(elem -> elem.get("name"))
                .collect(Collectors.toList());
    }

    public static int compateAge(Map<String, String> FirstElem, Map<String, String> SecondElem) {
        String rawFirstDate = FirstElem.get("birthday");
        String rawSecondDate = SecondElem.get("birthday");

        LocalDate firstDate = getDateFromString(rawFirstDate);
        LocalDate secondDate = getDateFromString(rawSecondDate);
         if (firstDate.isBefore(secondDate)) {
             return -1;
         } else if (firstDate.isAfter(secondDate)) {
             return 1;
         }
         return 0;
    }

    public static LocalDate getDateFromString(String rawDate) {
        String[] rawPartsDate = rawDate.split("-");
        int[] partsDate = new int[rawPartsDate.length];

        for (int i = 0; i < rawPartsDate.length; i++) {
            partsDate[i] = Integer.parseInt(rawPartsDate[i]);
        }

        return LocalDate.of(partsDate[0], partsDate[1], partsDate[2]);
    }
}

// END
