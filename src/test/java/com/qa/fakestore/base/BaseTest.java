package com.qa.fakestore.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;

import com.qa.fakestore.client.RestClient;
import com.qa.fakestore.configuration.ConfigurationManager;

public class BaseTest {

	//service urls :
	public static final String FAKESTORE_API_USER_ENDPOINT = "/users";
	public static final String FAKESTORE_API_PRODUCT_ENDPOINT = "/products";
	public static final String FAKESTORE_API_CART_ENDPOINT = "/carts";
	public static final String FAKESTORE_API_LOGIN_ENDPOINT = "/auth/login";
	
	protected ConfigurationManager configManager;
	protected Properties prop;
	protected RestClient restClient;
	
	@BeforeTest
	public void setup() {
		configManager = new ConfigurationManager();
		prop = configManager.initProp();
	}
}
