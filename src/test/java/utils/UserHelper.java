package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.User;

import static io.restassured.RestAssured.given;
import java.util.Map;
import java.util.HashMap;

public class UserHelper {

    private static final String REGISTER_ENDPOINT = UrlConstants.BASE_URL + "api/auth/register";
    private static final String DELETE_ENDPOINT = UrlConstants.BASE_URL + "api/auth/user";


    // Настройка базового URI для RestAssured
    static {
        RestAssured.baseURI = UrlConstants.BASE_URL;
    }


    /**
     * Метод для создания нового пользователя через API.
     *
     * @return объект пользователя с заполненными данными и токеном.
     */
    public static User createUser() {
        // Генерация данных пользователя
        String email = TestDataGenerator.generateEmail();
        String password = TestDataGenerator.generateValidPassword();
        String name = TestDataGenerator.generateName();

        // Отправка данных на регистрацию через API
        Response response = registerUser(email, password, name);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Не удалось создать пользователя: " + response.body().asString());
        }

        String token = response.jsonPath().getString("accessToken");
        return new User(email, password, name, token);
    }

    /**
     * Метод для удаления пользователя через API.
     *
     * @param user объект пользователя, содержащий токен для авторизации.
     */
    public static void deleteUser(User user) {
        Response response = deleteUserRequest(user.getToken());

        if (response.statusCode() == 202) {
            System.out.println("Пользователь " + user.getEmail() + " успешно удалён.");
        } else {
            System.out.println("Не удалось удалить пользователя " + user.getEmail() + ". Статус: " + response.statusCode());
            System.out.println("Ответ сервера: " + response.body().asString());
        }
    }

    /**
     * Вспомогательный метод для регистрации пользователя.
     *
     * @param email    email пользователя.
     * @param password пароль пользователя.
     * @param name     имя пользователя.
     * @return Response объект ответа от сервера.
     */
    private static Response registerUser(String email, String password, String name) {
        // Генерация данных через Map
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("name", name);

        return given()
                .header("Content-Type", "application/json")
                .body(requestBody)  // Используем Map
                .when()
                .post(REGISTER_ENDPOINT);
    }

    /**
     * Вспомогательный метод для удаления пользователя.
     *
     * @param token токен пользователя (без "Bearer").
     * @return Response объект ответа от сервера.
     */
    private static Response deleteUserRequest(String token) {
        // Используем Map для передачи токена
        Map<String, Object> authHeader = new HashMap<>();
        authHeader.put("Authorization", token);

        return given()
                .headers(authHeader)  // Передаем токен через Map
                .when()
                .delete(DELETE_ENDPOINT);
    }
}
