package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;


class App {
    public static void save(Path filePath, Car car) throws JsonProcessingException, IOException {
        Files.write(filePath, car.serialize().getBytes());
    }

    public static Car extract(Path filePath) throws IOException {
        String rawJson = Files.readString(filePath).trim();
        return Car.unserialize(rawJson);
    }
}
// END
