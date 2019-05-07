package twitterTests.tests.apiTests;

import twitterTests.configuration.ApiConfiguration;

public final class EndPoints{
    public static final String baseUri = "https://api.twitter.com/1.1/statuses";
    public static final String homeTimelineUri = "/home_timeline.json";
    public static final String postStatusesUri = "/update.json";
    public static final String deleteStatusesUri = "/destroy/:id.json";
    public static final String getTweetByIdUri = "/show.json?id=:id";
}
