package com.qa.fakestore.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;

import com.qa.fakestore.client.RestClient;
import com.qa.fakestore.configuration.ConfigurationManager;

public class BaseTest {

	protected ConfigurationManager configManager;
	protected Properties prop;
	protected RestClient restClient;
	
	@BeforeTest
	public void setup() {
		configManager = new ConfigurationManager();
		prop = configManager.initProp();
	}
}
