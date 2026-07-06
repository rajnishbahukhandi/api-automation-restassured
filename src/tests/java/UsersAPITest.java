import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersAPITest {
    //object stores everything about the request.
    //Common request specification
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .addHeader("x-api-key", "reqres_194ef351d4a44e7f8a57a90e5a311865")
            .addHeader("Accept", "application/json")
            .setContentType(ContentType.JSON)
            .build();

    // Common response specification
    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    @Test
    //Check the Response
    public void test_1(){
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

    @Test
    //Verify the empty data.
    // use RequestSpecification and ResponseSpecification to avoid code duplication.
    public void test_2(){
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
    public void test_3() {
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
    public void test_4() {
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
    public void test_5() {
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
}
