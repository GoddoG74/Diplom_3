package utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    // Сделаем поле 'FAKER' final
    private static final Faker FAKER = new Faker();

    public static String generateName() {
        return FAKER.name().fullName();  // Используем FAKER вместо faker
    }

    public static String generateEmail() {
        return FAKER.internet().emailAddress();  // Используем FAKER вместо faker
    }

    public static String generateValidPassword() {
        return FAKER.internet().password(6, 10); // Генерация пароля от 6 до 10 символов
    }

    public static String generateShortPassword() {
        return FAKER.internet().password(1, 5); // Генерация пароля с длиной от 1 до 5 символов
    }
}
