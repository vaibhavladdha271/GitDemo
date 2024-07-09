package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countries;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]")
	List<WebElement> countryDropdownresults;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]/button[2]")
	WebElement countriesButton;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeOrder;
	
	public void selectCountry()
	{
		Actions act=new Actions(driver);
		act.sendKeys(countries,"India").build().perform();
		countriesButton.click();
//		for(int i=0;i<countryDropdownresults.size();i++)
//		{
//			System.out.println(countryDropdownresults.get(i).getText());
//			if(countryDropdownresults.get(i).getText().equalsIgnoreCase("India"))
//			{
//				
//			}
//		}
	}
	public ConfirmationPage placeOrder()
	{
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}
