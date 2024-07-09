package rahulshettyacademy.tests;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import java.util.List;
import org.openqa.selenium.WebElement;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement product1=products.stream().filter(product->
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		product1.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#toast-container"),"Product Added To Cart"));
//		System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match=cartProducts.stream().anyMatch(p->p.getText().equals(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("div.subtotal button")).click();
		Actions act=new Actions(driver);
		act.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
		
		//driver.quit();
	}

}
