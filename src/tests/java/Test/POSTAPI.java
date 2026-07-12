package Test;

import BaseAPI.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POSTAPI extends BaseTest {

    @Test
    public void postloginSuccessTest_01() {

        String requestBody = """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "cityslicka"
                }
                """;

        io.restassured.response.Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .log().all()
        .when()
                .post("/api/login")
        .then()
                .spec(responseSpec)
                .log().all()
                .body("token", notNullValue())
                .extract().response();

        // extract token from the actual response (not from the spec)[authenticated requests]
        String token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
        Assert.assertNotNull(token);
    }
}
