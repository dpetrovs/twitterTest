package twitterTests.tests.apiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import twitterTests.configuration.ApiConfiguration;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth;

public class ApiBase extends ApiConfiguration{
    private List<String> id = new ArrayList<>();
    private String tweetDate;

    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(EndPoints.baseUri)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.ANY)
            .setAuth(oauth(getApiKey(), getApiSecretKey(), getAccessToken(), getAccessTokenSecret()))
            .log(LogDetail.ALL)
            .build();

    String getResponseParam(Response response, String param) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString(param);
    }

    int getStatusCode(Response response) {
        return response.statusCode();
    }

    Response getLastTweet() {
        return given().spec(requestSpec)
                .queryParam("count", "1")
                .when().get(EndPoints.homeTimelineUri)
                .then().extract().response();
    }

    Response getPostNewTweetResponse(String status) {
        Response response = given().spec(requestSpec)
                .queryParam("status", status)
                .when().post(EndPoints.postStatusesUri)
                .then().extract().response();
        if (response.jsonPath().getString("id") != null) {
            this.id.add(response.jsonPath().getString("id"));
            this.tweetDate = response.jsonPath().getString("created_at");
        }
        return response;
    }

    void postNewTweet(String status) {
        getPostNewTweetResponse(status);
    }

    Response getDeleteTweetByIdResponse(String id) {
        return given().spec(requestSpec)
                .when().post(EndPoints.deleteStatusesUri.replace(":id", id))
                .then().extract().response();
    }

    Response getTweetById(String id) {
        return given().spec(requestSpec)
                .when().get(EndPoints.getTweetByIdUri.replace(":id", id))
                .then().extract().response();
    }

    void deleteTweetById(String id) {
        given().spec(requestSpec).post(EndPoints.deleteStatusesUri.replace(":id", id));
    }

    String getDate(Response response){
       return getResponseParam(response, "[0].created_at");
    }

    String getLastTweetId() {
        return id.get(id.size() - 1);
    }

    List<String> getCreatedTweetsId() {
        return id;
    }

    String getTweetDate() {
        return tweetDate;
    }

    public void deleteCreatedTweets(List<String> ids) {
            ids.forEach(this::deleteTweetById);
    }

    public void deleteCreatedTweets(String id) {
        deleteTweetById(id);
    }
}
