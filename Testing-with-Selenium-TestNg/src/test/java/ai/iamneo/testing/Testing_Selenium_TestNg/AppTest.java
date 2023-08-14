package ai.iamneo.testing.Testing_Selenium_TestNg;

import java.util.*;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;

public class AppTest {
    WebDriver driver = null;
    String url = "https://in.ebay.com";
    ChromeOptions options = new ChromeOptions();
	
	@BeforeTest
	public void beforeTest() throws IOException{
		System.setProperty("webdriver.chrome.driver", "/home/coder/project/workspace/chromedriver");
		driver = new RemoteWebDriver(new URL("http://localhost:8080"), options);
	}
    @Test
    public void ebaySP() {
      
        driver.get(url);

        
        String productToSearch = "Apple Watches";
        driver.findElement(By.name("_nkw")).sendKeys(productToSearch);

        
        String categoryToSelect = "Electronics";
        driver.findElement(By.id("gh-cat")).sendKeys(categoryToSelect);

       
        driver.findElement(By.id("gh-btn")).click();
    }

	@Test
    public void printPR() {
        List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
        for (WebElement productResult : productResults) {
            System.out.println(productResult.getText());
        }
    }

    @Test
    public void printNthProduct(int n) {
        List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
        if (n > 0 && n <= productResults.size()) {
            System.out.println(productResults.get(n - 1).getText());
        } else {
            System.out.println("Invalid product index.");
        }
    }

	@Test
    public void printAllProductsFromFirstPage() {
        List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
        for (WebElement productResult : productResults) {
            System.out.println(productResult.getText());
        }
    }

    @Test
    public void printAllProductsWithScroll() {
        int pageNumber = 1;
        while (true) {
            List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
            if (productResults.isEmpty()) {
                break;
            }

            System.out.println("Page " + pageNumber + " Products:");
            for (WebElement productResult : productResults) {
                System.out.println(productResult.getText());
            }

            // Scroll down to the next page
            pageNumber++;
            driver.findElement(By.xpath("//a[text()='" + pageNumber + "']")).click();
        }
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}