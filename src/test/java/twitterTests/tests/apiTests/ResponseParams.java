package twitterTests.tests.apiTests;

import java.util.Arrays;

public enum ResponseParams {
    TEXT("text"),
    ID("id"),
    CREATED_AT("created_at");

    private String paramName;

    ResponseParams(String paramName) {
        this.paramName = paramName;
    }

    public static ResponseParams fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(ResponseParams.values())
                .filter(v -> v.paramName.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
