package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN

//Создайте класс App и публичный статический метод getForwardedVariables().
//        Метод принимает на вход содержимое конфигурационного файла в виде строки,
//        находит в нем переменные окружения, которые нужно передать и возвращает их
//        в виде строки формата "имя1=значение1,имя2=значение2,имя3=значение3,...".
//        map.entrySet().stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .map(e-> e.getKey().toString() + "=" + e.getValue().toString())
//                .collect(Collectors.joining (","));


class App {
    public static String getForwardedVariables(String fileContent) {
        String[] rowsContent = fileContent.split("\n");

        return Arrays.asList(rowsContent).stream()
                .filter(row -> row.contains("X_FORWARDED_"))
                .filter(row -> row.startsWith("environment"))
                .map(row -> row.replaceAll("environment=", ""))
                .map(row -> row.split(","))
                .map(row -> Arrays.asList(row))
                .flatMap(row -> row.stream())
                .map(row -> row.replaceAll(" ", ""))
                .filter(row -> row.contains("X_FORWARDED_"))
                .map(row -> row.replaceAll("X_FORWARDED_", ""))
                .map(row -> row.replaceAll("\"", ""))
                .collect(Collectors.joining(","));
    }
}
//END
