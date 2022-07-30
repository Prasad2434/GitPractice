package restassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import restassured.jsonutil.JsonPathUtil;
import restassured.request.UserAPI;

public class HttpMethodChaining {

	@Test
	public void testUserAPI() {
		// POST -> PUT -> GET -> DELETE -> GET
		RestAssured.baseURI = "http://localhost:8088/api/";

		// POST Request
		String responseBodyPOSTString =

				given().log().all().headers("Content-Type", "application/json").body(UserAPI.getPOSTRequestBody())
						.when().post("users").then().log().all().assertThat().statusCode(201).extract().asString();

		Integer createdUserID = Integer.valueOf((JsonPathUtil.getRequestedKeyFromResponse(responseBodyPOSTString, 
				"userId")));

				given().log().all().headers("Content-Type", "application/json").body(UserAPI.getPUTRequestBody())
						.pathParam("userId", createdUserID).when().put("users/{userId}").then().log().all().assertThat()
						.statusCode(200).extract().asString();

				given().log().all().pathParam("userId", createdUserID).when().get("users/{userId}").then().log().all()
						.assertThat().statusCode(200).extract().asString();


		// DELETE
		given().log().all().pathParam("userId", createdUserID).when().delete("users/{userId}").then().log().all()
				.assertThat().statusCode(204);

		// GET
		given().log().all().pathParam("userId", createdUserID).when().get("users/{userId}").then().log().all()
				.assertThat().statusCode(404);

	}

}
