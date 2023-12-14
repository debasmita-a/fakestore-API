package com.qa.fakestore.tests;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.fakestore.base.BaseTest;
import com.qa.fakestore.client.RestClient;
import com.qa.fakestore.constants.APIHttpStatus;
import com.qa.fakestore.pojo.Product;

public class ProductTest extends BaseTest{

	@BeforeMethod
	public void productsAPIsetup() {
		restClient = new RestClient(prop, prop.getProperty("baseURI"));
	}
	
	@Test
	public void getAllProductsTest() {
		restClient.get(FAKESTORE_API_PRODUCT_ENDPOINT, false)
		              .then()
		                 .assertThat()
		                      .statusCode(APIHttpStatus.OK_200.getCode())
		                           .and()
		                               .body("$", hasSize(20));	                             		             
	}
	
	@DataProvider
	public Object[][] getProductTestdata() {
		return new Object[][] {
			{2},{11},{13},{20}
		};
	}
	
	@Test(dataProvider = "getProductTestdata")
	public void getAProductTest(int productId) {
		restClient.get(FAKESTORE_API_PRODUCT_ENDPOINT + "/" + productId, true)
		              .then()
		                  .assertThat()
		                      .statusCode(APIHttpStatus.OK_200.getCode())
		                           .and()
		                               .body("id", equalTo(productId));
	}
	
	@DataProvider
	public Object[][] addProductTestdata() {
		return new Object[][]{
			{"Product 01", 34.45, "Test product 01", "https://i.pravatar.cc", "electronics"},
			{"Product 02", 323.45, "Test product 02", "https://i.pravatar.cc", "men's clothing"}
		};
	}
	
	@Test(dataProvider = "addProductTestdata")
	public void addProductTest(String productTitle, double productPrice, String productDesc, String productImg, String category) {
		Product product = Product.builder()
				                    .title(productTitle)
				                          .price(productPrice)
				                               .description(productDesc)
				                                  .image(productImg)
				                                       .category(category)
				                                          .build();
		
		int productId = restClient.post(FAKESTORE_API_PRODUCT_ENDPOINT, product, "javascript", true)
		              .then()
		                  .assertThat()
		                       .statusCode(APIHttpStatus.OK_200.getCode())
		                             .and()
		                                 .extract()
		                                      .jsonPath()
		                                           .get("id");
		System.out.println(productId);                                     
	}
	
	@DataProvider
	public Object[][] updateProductTestdata() {
		return new Object[][]{
			{"Product 01", 34.45, "Test product update 011", "https://i.pravatar.cc", "electronic", 2},
			{"Product 02", 323.45, "Test product update 022", "https://i.pravatar.cc", "men's clothing", 12}
		};
	}
	//Flow : Add a product first -- then update
	@Test(dataProvider = "updateProductTestdata")
	public void updateProductTest(String productTitle, double productPrice, String productDesc, String productImg, String category, int productId) {
		
		Product product = Product.builder()
                .title(productTitle)
                      .price(productPrice)
                           .description(productDesc)
                              .image(productImg)
                                   .category(category)
                                      .build();
		restClient.put(FAKESTORE_API_PRODUCT_ENDPOINT + "/" + productId, product, "javascript", true)
		              .then()
		                   .assertThat()
		                        .statusCode(APIHttpStatus.OK_200.getCode())
		                             .and()
		                                 .body("id", equalTo(productId));
	}
	
	//Flow : Add a product first -- then delete
	@Test(dataProvider = "getProductTestdata")
	public void deleteAProduct(int productId) {
		String productTitle = restClient.delete(FAKESTORE_API_PRODUCT_ENDPOINT + "/" + productId, true)
		               .then()
		                    .assertThat()
		                        .statusCode(APIHttpStatus.OK_200.getCode())
		                            .and()
		                                .body("id", equalTo(productId))
		                                     .extract()
		                                         .jsonPath()
		                                             .get("title");
		System.out.println("Product deleted : "+ productTitle);
	}
	
	@Test
	public void getAllCategoryTest() {
		
	}
	
	//ObjectMapper -- get the created product json and map to Product object
}
