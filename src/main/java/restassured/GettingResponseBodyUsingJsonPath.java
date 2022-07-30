package restassured;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import restassured.request.UserAPI;

public class GettingResponseBodyUsingJsonPath {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://localhost:8088/api/";
		
		String responseBody = 
		
		given()
			  .log()
			  .all()
			  .headers("Content-Type", "application/json")
			  .body(UserAPI.getPOSTRequestBody()).
		when()
			  .post("users").
		then()
			  .log()
			  .all()
		      .assertThat()
		      .statusCode(201)
		      .extract()
		      .body()      //Optional You can have body(), response(), headers()
		      .asString(); 
		
		System.out.println(responseBody);
		
		JsonPath responseBodyJSON = new JsonPath(responseBody);
		
		System.out.println(responseBodyJSON.getString("lastName"));
	}
}
