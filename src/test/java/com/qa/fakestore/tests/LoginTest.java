package com.qa.fakestore.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.fakestore.base.BaseTest;
import com.qa.fakestore.client.RestClient;
import com.qa.fakestore.pojo.User;
import com.qa.fakestore.pojo.User.Address;
import com.qa.fakestore.pojo.User.Address.Geolocation;
import com.qa.fakestore.pojo.User.Name;

import io.restassured.response.Response;

public class LoginTest extends BaseTest{

	@BeforeMethod
	public void loginAPIsetup() {
		restClient = new RestClient(prop, prop.getProperty("baseURI"));
	}
	
	@Test
	public void userLoginTest() {
		//add a new  user -- POST
		Geolocation geo = Geolocation.builder().lat("-37.8293").longi("89.456").build();
		Address address = Address.builder().city("cooco city").street("pooby street").number(3).zipcode("256002").geolocation(geo).build();
		Name name = Name.builder().firstname("debasmita").lastname("adhikari").build();
		User user = User.builder().email("fakestore_api" + Math.random()*1000 + "@gmail.com").username("pooby@12").password("cooco78!")
				.name(name).address(address).phone("23626288").build();
		
		Response responsePost = restClient.post("/users", user, "javascript", true);
		responsePost.then().assertThat().statusCode(200);
		int userID = responsePost.jsonPath().get("id");
		System.out.println(userID);
		
		//get the user with the user ID -- GET
		restClient = new RestClient(prop, prop.getProperty("baseURI"));
		Response responseGet = restClient.get("/users/" + userID, true);
		responseGet.prettyPrint();
		String username = responseGet.jsonPath().get("username");
		String password = responseGet.jsonPath().get("password");
		
		
		//login with username and password -- POST
		restClient = new RestClient(prop, prop.getProperty("baseURI"));
		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("username", username);
		requestBody.put("password", password);
		Response responseAuth = restClient.post("/auth/login", requestBody, "json", true);
		responseAuth.prettyPrint();
		String authToken = responseAuth.jsonPath().get("token");
		System.out.println(authToken);
		
		
	}
}
