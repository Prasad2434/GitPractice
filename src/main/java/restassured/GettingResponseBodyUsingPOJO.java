package restassured;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import restassured.model.User;
import restassured.request.UserAPI;

public class GettingResponseBodyUsingPOJO {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://localhost:8088/api/";
		
	    User userResponse = 
		given()
			  .headers("Content-Type", "application/json")
			  .body(UserAPI.getPOSTRequestBody()).
		when()
			  .post("users").
		then()
		      .assertThat()
		      .statusCode(201)
		      .extract()
		      .as(User.class);
		
	    System.out.println(userResponse);
	    System.out.println(userResponse.getAddress().getCity().equalsIgnoreCase("Baku"));
	}
}
