package twitterTests.configuration;

import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ApiConfiguration {
    private Properties properties = getProperties();
    private final String accessToken = properties.getProperty("access.token");
    private final String accessTokenSecret = properties.getProperty("access.token.secret");
    private final String apiKey = properties.getProperty("consumer.api.key");
    private final String apiSecretKey = properties.getProperty("consumer.api.secret.key");

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
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecretKey() {
        return apiSecretKey;
    }
}
