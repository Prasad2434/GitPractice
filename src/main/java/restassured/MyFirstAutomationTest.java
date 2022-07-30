package restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import restassured.request.UserAPI;

public class MyFirstAutomationTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://localhost:8088/api/";
		
		given().log()
			   .all()
			   .headers("Content-Type", "application/json")
			   .body(UserAPI.getPOSTRequestBody()).
		when().post("users").
		then().log()
		      .all()
		      .assertThat()
		      .statusCode(201)
		      .body("salary", equalTo(500000))
		      .header("Connection", "keep-alive");
	}

}
