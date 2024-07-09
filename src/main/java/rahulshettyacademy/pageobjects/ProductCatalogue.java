package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animatingIcon;
	
	By productsBy=By.cssSelector(".mb-3");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement product1=getProductsList().stream().filter(product->
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return product1;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement product1=getProductByName(productName);
		product1.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(animatingIcon);
	}
	
}
