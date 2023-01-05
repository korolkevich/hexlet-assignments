package exercise;

import java.util.List;
import java.util.stream.Collectors;
//Создайте класс App с публичным статическим методом buildApartmentsList().
//        Метод принимает в качестве первого аргумента список List объектов недвижимости,
//        реализующих интерфейс Home. Метод сортирует объекты по площади по возрастанию,
//        берет первые n элементов и возвращает строковые представления этих объектов в виде списка List.
//        Количество n элементов передаётся в метод buildApartmentsList() вторым параметром.
// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> homes, int limit) {
        return homes.stream()
                .map(home -> home.toString())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
// END
