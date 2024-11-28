package utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigReader.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static List<String> getBrowserList() {
        String browsers = properties.getProperty("browsers");
        if (browsers == null || browsers.isEmpty()) {
            throw new RuntimeException("No browsers specified in application.properties");
        }
        return Arrays.asList(browsers.split(","));
    }
}
