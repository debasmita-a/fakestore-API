package com.qa.fakestore.utils;

public class FrameworkUtils {

	public String generateRandomEmail() {
		return "fakestore_api" + Math.random()*1000 + "@gmail.com";
	}
}
