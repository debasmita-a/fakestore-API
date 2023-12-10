package com.qa.fakestore.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.fakestore.base.BaseTest;
import com.qa.fakestore.client.RestClient;

public class ProductTest extends BaseTest{

	@BeforeMethod
	public void productsAPIsetup() {
		restClient = new RestClient(prop, prop.getProperty("baseURI"));
	}
	
	@Test
	public void getAllProductsTest() {
		restClient.get("/products", false)
		          .then()
		          .assertThat()
		          .statusCode(200);
	}
}
