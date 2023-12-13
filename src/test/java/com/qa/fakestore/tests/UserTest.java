package com.qa.fakestore.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import com.qa.fakestore.base.BaseTest;
import com.qa.fakestore.client.RestClient;
import com.qa.fakestore.constants.APIHttpStatus;
import com.qa.fakestore.pojo.User;
import com.qa.fakestore.pojo.User.Address;
import com.qa.fakestore.pojo.User.Address.Geolocation;
import com.qa.fakestore.pojo.User.Name;

import io.restassured.http.ContentType;

public class UserTest extends BaseTest{

	public static int userId;
	
	@BeforeMethod
	public void userTestSetup() {
		restClient = new RestClient(prop, prop.getProperty("baseURI"));
	}
	
	@Test
	public void addUserTest() {
		Geolocation geo = Geolocation.builder().lat("-37.8293").longi("89.456").build();
		Address address = Address.builder().city("cooco city").street("pooby street").number(3).zipcode("256002").geolocation(geo).build();
		Name name = Name.builder().firstname("debasmita").lastname("adhikari").build();
		User user = User.builder().email("fakestore_api" + Math.random()*1000 + "@gmail.com").username("pooby@12").password("cooco78!")
				.name(name).address(address).phone("23626288").build();
		
		userId = restClient.post(FAKESTORE_API_USER_ENDPOINT, user, "javascript", true)
		             .then().log().all()
		                  .assertThat()
		                      .statusCode(APIHttpStatus.OK_200.getCode())
		                          .and()
		                              .contentType(ContentType.JSON)
		                                  .extract()
		                                      .jsonPath().get("id");
	}
	
	@Test
	public void getUserTest() {
		restClient.get(FAKESTORE_API_USER_ENDPOINT + "/" + 1, true)
		                .then()
		                     .assertThat() 
		                         .statusCode(APIHttpStatus.OK_200.getCode())
		                               .and()
		                                   .body("address.city", equalTo("kilcoole"));
		                                        
	}
	
	@Test
	public void getAllUsersTest() {
		restClient.get(FAKESTORE_API_USER_ENDPOINT, true)
		             .then()
		                 .assertThat()
		                       .statusCode(APIHttpStatus.OK_200.getCode())
		                           .and()
		                                 .body("$", hasSize(10));
	}
}
