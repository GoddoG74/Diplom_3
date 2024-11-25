package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserHelper {

    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String DELETE_ENDPOINT = "/api/auth/user";

    // Настройка базового URI для RestAssured
    static {
        RestAssured.baseURI = BASE_URI;
    }


    public static User createUser() {
        String email = TestDataGenerator.generateEmail();
        String password = TestDataGenerator.generateValidPassword();
        String name = TestDataGenerator.generateName();

        Response response = registerUser(email, password, name);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Не удалось создать пользователя: " + response.body().asString());
        }

        String token = response.jsonPath().getString("accessToken");
        return new User(email, password, name, token);
    }


    public static void deleteUser(User user) {
        Response response = deleteUserRequest(user.getToken());

        if (response.statusCode() == 202) {
            System.out.println("Пользователь " + user.getEmail() + " успешно удалён.");
        } else {
            System.out.println("Не удалось удалить пользователя " + user.getEmail() + ". Статус: " + response.statusCode());
            System.out.println("Ответ сервера: " + response.body().asString());
        }
    }


    private static Response registerUser(String email, String password, String name) {
        String requestBody = String.format(
                "{ \"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\" }",
                email, password, name
        );

        return given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(REGISTER_ENDPOINT);
    }


    private static Response deleteUserRequest(String token) {
        return given()
                .header("Authorization", token) // Передача токена напрямую, без 'Bearer'
                .when()
                .delete(DELETE_ENDPOINT);
    }
}
