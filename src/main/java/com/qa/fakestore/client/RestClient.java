package com.qa.fakestore.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.qa.fakestore.frameworkexception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private Properties prop;
	private String baseURI;
	
	private RequestSpecBuilder specBuilder;
	
	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}
	
	private void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		case "javascript":
			Map<String, String> formParamsMap = new HashMap<String, String>();
			formParamsMap.put("Content-Type", "application/javascript");
			specBuilder.addQueryParams(formParamsMap);
			break;

		default:
			System.out.println("Please provide correct content type." + contentType);
			throw new APIFrameworkException("INVALIDCONTENTTYPE");
		}
	}
	
	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(baseURI);
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Map<String, String> headersMap) {
		specBuilder.setBaseUri(baseURI);
		if(headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}		
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParamsMap) {
		specBuilder.setBaseUri(baseURI);
		if(headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}		
		if(queryParamsMap != null) {
			specBuilder.addQueryParams(queryParamsMap);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if(requestBody != null) {
			specBuilder.setBody(requestBody);
		}		
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap) {
		specBuilder.setBaseUri(baseURI);
		setRequestContentType(contentType);
		if(headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}
	
	// Http method utils : GET
	
	public Response get(String serviceUrl, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec()).log().all().when().get(serviceUrl);
		}		
		return RestAssured.given(createRequestSpec()).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String, String> headersMap, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(headersMap)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String, String> headersMap, Map<String, Object> queryParamsMap, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(headersMap, queryParamsMap)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap, queryParamsMap)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Object requestBody, String contentType, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().get(serviceUrl);
	}
	
	// POST
	
	public Response post(String serviceUrl, Object requestBody, String contentType, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().post(serviceUrl);
	}
	
	public Response post(String serviceUrl, Object requestBody, String contentType, Map<String, String> headersMap, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().post(serviceUrl);
	}
	
	
	
	// PUT
	
	public Response put(String serviceUrl, Object requestBody, String contentType, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().put(serviceUrl);
	}
	
	public Response put(String serviceUrl, Object requestBody, String contentType, Map<String, String> headersMap, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().put(serviceUrl);
	}
	
	// PATCH
	
	public Response patch(String serviceUrl, Object requestBody, String contentType, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl, Object requestBody, String contentType, Map<String, String> headersMap, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().patch(serviceUrl);
	}
	
	// DELETE
	
	public Response delete(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().delete();					
		}		
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().delete();
	}
	
	public Response delete(String serviceUrl, boolean log) {
		if(log == true) {
			return RestAssured.given(createRequestSpec()).log().all().delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec()).delete(serviceUrl);
	}
	
	
}
