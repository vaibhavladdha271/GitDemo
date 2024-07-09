package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(xpath="//table[contains(@class,'table-hover')]/tbody/tr/td[2]")
	List<WebElement> products;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean orderMatch=products.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return orderMatch;
	}
	
	
}
