package twitterTests.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ApiConfiguration {
    private Properties properties = getProperties();
    private final String ACCESS_TOKEN = properties.getProperty("access.token");
    private final String ACCESS_TOKEN_SECRET = properties.getProperty("access.token.secret");
    private final String API_KEY = properties.getProperty("consumer.api.key");
    private final String API_SECRET_KEY = properties.getProperty("consumer.api.secret.key");

    private Properties getProperties() {
            Properties properties = new Properties();
            try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
                properties.load(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
    }

    public String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public String getAccessTokenSecret() {
        return ACCESS_TOKEN_SECRET;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public String getApiSecretKey() {
        return API_SECRET_KEY;
    }
}
