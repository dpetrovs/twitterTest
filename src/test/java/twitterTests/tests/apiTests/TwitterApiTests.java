package twitterTests.tests.apiTests;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class TwitterApiTests extends ApiBase{
    @Test
    public void verifyCreatedTweetParameters() {
        SoftAssertions softly = new SoftAssertions();
        getPostNewTweetResponse("New tweet 8");
        Response response = getLastTweet();
        softly.assertThat(getStatusCode(response)).isEqualTo(200);
        softly.assertThat(getResponseParam(response, "[0].text")).isEqualTo("New tweet 8");
        softly.assertThat(getResponseParam(response, "[0].retweet_count")).isEqualTo("0");
        softly.assertThat(getDate(response)).isEqualTo(getTweetDate());
        softly.assertAll();
    }

    @Test
    public void verifyDeletedTweetStatus() {
        getPostNewTweetResponse("New tweet 8");
        deleteTweetById(getLastTweetId());
        Assert.assertEquals(404, getStatusCode(getTweetById(getLastTweetId())));
    }

    @Test
    public void verifyTweetDuplicationCausesError() {
        postNewTweet("New tweet 8");
        Response response = getPostNewTweetResponse("New tweet 8");
        Assert.assertEquals(403, getStatusCode(response));
    }

    @After
    public void tearDown() {
            deleteCreatedTweets(getCreatedTweetsId());
    }
}
