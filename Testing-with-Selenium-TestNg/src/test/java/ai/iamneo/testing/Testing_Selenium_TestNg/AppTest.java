package ai.iamneo.testing.Testing_Selenium_TestNg;

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
    public void ebaySearchProduct() {
        // Open eBay website
        driver.get(url);

        // Enter the product in the search box
        String productToSearch = "Apple Watches";
        driver.findElement(By.name("_nkw")).sendKeys(productToSearch);

        // Select the category from the dropdown
        String categoryToSelect = "Electronics";
        driver.findElement(By.id("gh-cat")).sendKeys(categoryToSelect);

        // Click the Search button
        driver.findElement(By.id("gh-btn")).click();
    }

    // Method to print the result of the product
    public void printProductResult() {
        List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
        for (WebElement productResult : productResults) {
            System.out.println(productResult.getText());
        }
    }

    // Method to print Nth product (generic method)
    public void printNthProduct(int n) {
        List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
        if (n > 0 && n <= productResults.size()) {
            System.out.println(productResults.get(n - 1).getText());
        } else {
            System.out.println("Invalid product index.");
        }
    }

    // Method to print all products from the 1st page
    public void printAllProductsFromFirstPage() {
        List<WebElement> productResults = driver.findElements(By.xpath("//li[contains(@id, 'item')]"));
        for (WebElement productResult : productResults) {
            System.out.println(productResult.getText());
        }
    }

    // Method to print all products along with a scroll down
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
