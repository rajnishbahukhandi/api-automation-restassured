import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersAPITest {
    @Test
    //Check the Response
    public void test_1(){
        RestAssured.baseURI = "https://reqres.in";
        Response response = given()
                .header("x-api-key", "reqres_194ef351d4a44e7f8a57a90e5a311865")
                .header("Accept", "application/json")
                .log().all()
                .when()
                .get("/api/users");
        System.out.println("StatusCode: "+response.getStatusCode());
        System.out.println("ResponseTime: "+response.getTime());
        System.out.println("String: "+response.getBody().asString());
        System.out.println("StatusLine: "+response.getStatusLine());
        System.out.println("Header: "+response.getHeader("Content-Type"));
        int statuscode = response.getStatusCode();
        Assert.assertEquals(+statuscode, 200);

    }
    @Test
    //Verify the empty data.
    public void test_2(){
        RestAssured.baseURI = "https://reqres.in";
        Response response = given()
                .header("x-api-key", "reqres_194ef351d4a44e7f8a57a90e5a311865")
                .header("Accept", "application/json")
                .log().all()
                .when()
                .get("/api/users")
                .then()
                .log().all()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200);

        response.then()
                .body("page", equalTo(1))
                .body("data", not(empty()));
    }

    @Test
    //Verify the json data.
    public void test_3() {
        RestAssured.baseURI = "https://reqres.in";
        Response response = given()
                .header("x-api-key", "reqres_194ef351d4a44e7f8a57a90e5a311865")
                .header("Accept", "application/json")
                .log().all()
                .when()
                .get("/api/users/2");

        response.then()
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
        System.out.println("User Data: " + response.jsonPath().getMap(""));
    }

    @Test
    //Verify the first user data with Map collection.
    public void test_4() {
        RestAssured.baseURI = "https://reqres.in";
        Response response = given()
                .header("x-api-key", "reqres_194ef351d4a44e7f8a57a90e5a311865")
                .header("Accept", "application/json")
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
        RestAssured.baseURI = "https://reqres.in";
        Response response = given()
                .header("x-api-key", "reqres_194ef351d4a44e7f8a57a90e5a311865")
                .header("Accept", "application/json")
                .log().all()
                .when()
                .get("/api/users");
        List<Map<String, Object>> users = response.jsonPath().getList("data");

        for (Map<String, Object> user : users) {
            System.out.println("First Name: " + user.get("first_name"));
        }
    }
}
