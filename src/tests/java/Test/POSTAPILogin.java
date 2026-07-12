package Test;

import BaseAPI.BaseTest;
import Payloads.LoginPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POSTAPILogin extends BaseTest {

    @Test
    public void postloginSuccessTest_01() {
        // login Success Test
        String requestBody = LoginPayload.loginSuccess();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
//                .log().all()
        .when()
                .post("/api/login")
        .then()
                .spec(responseSpec)
//                .log().all()
                .body("token", notNullValue())
                .extract().response();

        // extract token from the actual response (not from the spec)[authenticated requests]
        String token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
        Assert.assertNotNull(token);
    }

    @Test
    public void postloginFailureTest_02() {
        // Negative test case to check if the API returns an error for invalid credentials.
        String requestBody = LoginPayload.loginFailure();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginMissingPasswordTest_03() {
        // Negative test case to check if the API returns an error for missing password.
        String requestBody = LoginPayload.loginMissingPassword();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("Missing password"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Missing password");
    }

    @Test
    public void postloginMissingEmailTest_04() {
        // Negative test case to check if the API returns an error for missing email.
        String requestBody = LoginPayload.loginMissingEmail();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("Missing email or username"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Missing email or username");
    }

    @Test
    public void postloginEmptyTest_05() {
        // Negative test case to check if the API returns an error for empty request body.
        String requestBody = LoginPayload.loginEmpty();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("Missing email or username"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "Missing email or username");
    }

    @Test
    public void postloginInvalidEmailFormatTest_06() {
        // Negative test case to check if the API returns an error for invalid email format.
        String requestBody = LoginPayload.loginInvalidEmailFormat();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidPasswordFormatTest_07() {
        // Negative test case to check if the API returns an error for invalid password format.
        String requestBody = LoginPayload.loginInvalidPasswordFormat();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
   public void postloginWithExtraFieldsShouldReturnTokenTest_08(){
        //It's a positive robustness test that checks whether the API ignores unexpected fields instead of failing.
        String requestBody = LoginPayload.loginWithExtraFieldsShouldReturnToken();

        given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(200)
                .log().all()
                .body("token", notNullValue());
   }

   @Test
   public void loginBoundaryMaxLengthFieldsTestingTest_09(){
        //Boundary testing is not applicable for login API as it only accepts email and password fields.
        //However, we can test the maximum length of email and password fields to ensure that the API handles them correctly.
        String requestBody = LoginPayload.loginBoundaryMaxLengthFieldsTesting();

        given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(200)
                .log().all()
                .body("token", notNullValue());
   }

   @Test
   public void postloginInvalidEmailDomainTest_10() {
        //Negative test case to check if the API returns an error for invalid email domain.
        String requestBody = LoginPayload.loginInvalidEmailDomain();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailCaseSensitivityTest_11() {
        //Negative test case to check if the API returns an error for invalid email case sensitivity.
        String requestBody = LoginPayload.loginInvalidEmailCaseSensitivity();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailMissingAtSymbolTest_12() {
        //Negative test case to check if the API returns an error for invalid email missing @ symbol.
        String requestBody = LoginPayload.loginInvalidEmailMissingAtSymbol();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailMissingDomainTest_13() {
        //Negative test case to check if the API returns an error for invalid email missing domain.
        String requestBody = LoginPayload.loginInvalidEmailMissingDomain();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailMultipleAtSymbolsTest_14() {
        //Negative test case to check if the API returns an error for invalid email with multiple @ symbols.
        String requestBody = LoginPayload.loginInvalidEmailMultipleAtSymbols();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailLeadingDotTest_15() {
        //Negative test case to check if the API returns an error for invalid email with leading dot.
        String requestBody = LoginPayload.loginInvalidEmailLeadingDot();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailSpecialCharactersInDomainTest_16() {
        //Negative test case to check if the API returns an error for invalid email with special characters in domain.
        String requestBody = LoginPayload.loginInvalidEmailSpecialCharactersInDomain();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }

    @Test
    public void postloginInvalidEmailWithSpacesTest_17() {
        //Negative test case to check if the API returns an error for invalid email with spaces.
        String requestBody = LoginPayload.loginInvalidEmailWithSpaces();

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .statusCode(400)
                .log().all()
                .body("error", equalTo("user not found"))
                .extract().response();

        // extract error message from the actual response
        String errorMessage = response.jsonPath().getString("error");
        System.out.println("Error Message: " + errorMessage);
        Assert.assertEquals(errorMessage, "user not found");
    }
}
