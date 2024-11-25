package utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    // Сделаем поле 'faker' final
    private static final Faker faker = new Faker();

    public static String generateName() {
        return faker.name().fullName();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }


    public static String generateValidPassword() {
        return faker.internet().password(6, 10); // Генерация пароля от 6 до 10 символов
    }

    public static String generateShortPassword() {
        return faker.internet().password(1, 5); // Генерация пароля с длиной от 1 до 5 символов
    }
}
