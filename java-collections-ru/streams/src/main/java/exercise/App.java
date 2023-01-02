package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(email -> isFreeEmail(email))
                .count();
    }

    public static boolean isFreeEmail(String email) {
        List<String> freeDomains = List.of("@gmail.com", "@yandex.ru", "@hotmail.com");
        for (var domain: freeDomains) {
            if (email.contains(domain)) {
                return true;
            }
        }
        return false;
    }
}

// END
