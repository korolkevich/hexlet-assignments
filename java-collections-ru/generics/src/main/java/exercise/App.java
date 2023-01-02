package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> listBooks, Map<T, T> where) {
        List<Map<T, T>> resultBooks = new ArrayList<>();
        for (var book: listBooks) {
            if (checkIsValid(book, where)) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }

    public static <T> boolean checkIsValid(Map<T, T> listElem, Map<T, T> queryFields) {
        for (Map.Entry<T, T> field: queryFields.entrySet()) {
            T fieldName = field.getKey();
            T elemValue = listElem.get(fieldName);
            T queryValue = field.getValue();

            if (!elemValue.equals(queryValue)) {
                return false;
            }
        }
        return true;
    }
}
//END
