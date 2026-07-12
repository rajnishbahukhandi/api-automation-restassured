package Test;
import BaseAPI.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GETAPI extends BaseTest {
    @Test
    public void getUsersTest_01() {
        given()
                .spec(requestSpec)
                .log().all()
        .when()
                .get("/api/users")
        .then()
                .spec(responseSpec)
                .log().all();
    }

    @Test
    //Verify the empty data.
    // use RequestSpecification and ResponseSpecification to avoid code duplication.
    public void getUsersTest_02(){
        given()
                //Use the RequestSpecification
                .spec(requestSpec)
                .log().all()
                .when()
                .get("/api/users")
                .then()
                //Use the ResponseSpecification
                .spec(responseSpec)
                .log().all()
                //Additional assertions
                .body("page", equalTo(1))
                .body("data", not(empty()));
    }

    @Test
    //Verify the json data.
    public void getUsersTest_03() {
        Response response = given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get("/api/users/2")
                .then()
                .spec(responseSpec)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                //Extract(Save) the response to a variable for further processing.
                .extract()
                .response();
        System.out.println("User Data: " + response.jsonPath().getMap("data"));
    }

    @Test
    //Verify the first user data with Map collection.
    public void getUsersTest_04() {
        Response response = given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get("/api/users");
        Map<String, Object> firstUser = response.jsonPath().getMap("data[0]");
        System.out.println("First User Data: " + firstUser);
        System.out.println("ID: " + firstUser.get("id"));
        System.out.println("First Name: " + firstUser.get("first_name"));
        System.out.println("Last Name: " + firstUser.get("last_name"));
        System.out.println("Email: " + firstUser.get("email"));
    }

    @Test
    //Verify the first user data with List collection.
    public void getUsersTest_05() {
        Response response = given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get("/api/users");
        List<Map<String, Object>> users = response.jsonPath().getList("data");
        for (Map<String, Object> user : users) {
            System.out.println("First Name: " + user.get("first_name"));
        }
    }

    @Test
    //Check the Response
    public void getUsersTest_06(){
        //the server sends back a response.
        //Now the response object contains.
        Response response = given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get("/api/users");
        // Methods belong to the Response class, not RequestSpecification.
        System.out.println("StatusCode: "+response.getStatusCode());
        System.out.println("ResponseTime: "+response.getTime());
        System.out.println("String: "+response.getBody().asString());
        System.out.println("StatusLine: "+response.getStatusLine());
        System.out.println("Header: "+response.getHeader("Content-Type"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
