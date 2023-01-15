package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        // Получаем инстанс приложения
        app = App.getApp();
        // Запускаем приложение на рандомном порту
        app.start(0);
        // Получаем порт, на которм запустилось приложение
        int port = app.port();
        // Формируем базовый URL
        baseUrl = "http://localhost:" + port;
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testPositive() {

        String firstName = "Aleksandr";
        String lastName = "Vasiliev";
        String email = "aleks@yandex.ru";
        String password = "123456";

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asEmpty();

        assertThat(responsePost.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo(firstName);
        assertThat(actualUser.getLastName()).isEqualTo(lastName);
        assertThat(actualUser.getEmail()).isEqualTo(email);
    }

    @Test
    void testNegative() {

        String firstName = "";
        String lastName = "Petroff";
        String email = "yandex.ru";
        String password = "as";

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);

        User actualUser = new QUser()
                .lastName.equalTo(lastName)
                .findOne();

        assertThat(actualUser).isNull();

        String content = responsePost.getBody();

        assertThat(content).contains("yandex.ru");
        assertThat(content).contains("Petroff");
        assertThat(content).contains("Имя не должно быть пустым");
        assertThat(content).contains("Должно быть валидным email");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}
