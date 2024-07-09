package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorValidation"})
	public void loginValidationWithIncorrectUsernameAndPassword() throws Exception {
		landingPage.loginApplication("anshika@gmail.com", "Iamking@0");
		String actualError=landingPage.getErrorMessage();
		String expectedError="Incorrect email password.";
		Assert.assertTrue(actualError.equalsIgnoreCase(expectedError));
	}
	

}
