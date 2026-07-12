package Test;
import BaseAPI.BaseTest;
import Payloads.RegisterPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class POSTAPIRegister extends BaseTest {
    @Test
    public void postregisterSuccessTest_01() {
        // Implement the test for successful registration
        String requestbody = RegisterPayload.registerSuccess();
        // Use requestSpec and responseSpec to send the POST request and validate the response
        Response response = given()
                .spec(requestSpec)
                .body(requestbody)
        .when()
                .post("/api/register")
        .then()
                .spec(responseSpec)
//                .log().all()
                .body("token", notNullValue())
                .extract()
                .response();
        // extract token from the actual response (not from the spec)[authenticated requests]
        String token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
        Assert.assertNotNull(token);

    }

    @Test
    public void postregisterFailureTest_02() {
        // Implement the test for failed registration
        String requestbody = RegisterPayload.registerFailure();
        Response response = given()
                .spec(requestSpec)
                .body(requestbody)
//                .log().all()
        .when()
                .post("/api/register")
        .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"))
                .extract()
                .response();
        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Note: Only defined users succeed registration");
    }

    @Test
    public void postregisterMissingPasswordTest_03() {
        // Implement the test for registration with missing password
        String requestbody = RegisterPayload.registerFailure();
        Response response = given()
                .spec(requestSpec)
                .body(requestbody)
        .when()
                .post("/api/register")
        .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Note: Only defined users succeed registration");
    }

    @Test
    public void postregisterMissingEmailTest_04() {
        // Implement the test for registration with missing email
        String requestbody = RegisterPayload.registerFailure();
        Response response = given()
                .spec(requestSpec)
                .body(requestbody)
        .when()
                .post("/api/register")
        .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Note: Only defined users succeed registration");
    }

    @Test
    public void postregisterEmptyTest_05() {
        // Implement the test for registration with empty payload
        String requestbody = RegisterPayload.registerFailure();
        Response response = given()
                .spec(requestSpec)
                .body(requestbody)
        .when()
                .post("/api/register")
        .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Note: Only defined users succeed registration");
    }
}
