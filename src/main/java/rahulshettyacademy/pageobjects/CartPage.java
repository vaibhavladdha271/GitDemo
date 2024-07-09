package rahulshettyacademy.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> productTitle;
	
	@FindBy(css="div.subtotal button")
	WebElement checkoutBtn;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match=productTitle.stream().anyMatch(p->p.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage()
	{
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
}
