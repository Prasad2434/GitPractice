package BookApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetApi 
{
	@Test
	public void getapiTest()
	{
		RestAssured.baseURI="http://localhost:8088/api";
	}

}
