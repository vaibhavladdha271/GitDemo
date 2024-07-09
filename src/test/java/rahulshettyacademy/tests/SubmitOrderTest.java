package rahulshettyacademy.tests;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"PurchaseOrder"},retryAnalyzer=Retry.class)
	public void submitOrderTest(HashMap<String,String> input) throws Exception {
		String expectedconfirmationMessage = "THANKYOU FOR THE ORDER.";
		ProductCatalogue productPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productPage.getProductsList();
		productPage.addProductToCart(productName);
//		System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());
		CartPage cart = productPage.goToCart();
		Boolean match = cart.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkout = cart.goToCheckoutPage();
		checkout.selectCountry();
		ConfirmationPage confirmPage = checkout.placeOrder();
		String actualConfirmationMessage = confirmPage.verifyConfirmationMessage();
		Assert.assertTrue(actualConfirmationMessage.equalsIgnoreCase(expectedconfirmationMessage));
	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void OrderHistoryTest() {
		ProductCatalogue productPage = landingPage.loginApplication("shetty@gmail.com", "Iamking@000");
		OrderPage orders = productPage.goToOrdersSection();
		Boolean match = orders.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws Exception
	{
		List<HashMap<String,String>> jsonData=getJsonData(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//Purchase.json");
		return new Object[][] {{jsonData.get(0)}};
		
	}
	//@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("productName", "ZARA COAT 3");
//		
////		HashMap<String,String> map1=new HashMap<String,String>();
////		map1.put("email", "shetty@gmail.com");
////		map1.put("password", "Iamking@000");
////		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		
//		return new Object[][] {{map}};
//
//	}

}
