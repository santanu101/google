package google.google;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class google {
	private WebDriver driver;
	private int invalidLinksCount;
	@BeforeClass 
		// TODO Auto-generated method stub
		
		public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","E:\\Jars\\chrome_driver_86\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://google.com");
		Thread.sleep(5000);
			driver.findElement(By.xpath("//body/div[@id='viewport']/div[@id='searchform']/form[@id='tsf']/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys("India");
			WebElement webElement = driver.findElement(By.className("gNO89b"));
			webElement.sendKeys(Keys.TAB);
			webElement.click();
		}
	@Test(priority = 1)
	public void countlink() {
	List<WebElement> links = driver.findElements(By.tagName("a"));
    System.out.println("The number of links is" + links.size());
	}
		@Test(priority = 2)
		public void validateInvalidLinks() {

			try {
				invalidLinksCount = 0;
				List<WebElement> anchorTagsList = driver.findElements(By
						.tagName("a"));
				System.out.println("Total no. of links are "
						+ anchorTagsList.size());
				for (WebElement anchorTagElement : anchorTagsList) {
					if (anchorTagElement != null) {
						String url = anchorTagElement.getAttribute("href");
						if (url != null && !url.contains("javascript")) {
							verifyURLStatus(url);
						} else {
							invalidLinksCount++;
						}
					}
				}

				System.out.println("Total no. of invalid links are "
						+ invalidLinksCount);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

		private void verifyURLStatus(String url) {
			// TODO Auto-generated method stub
			
		}


		@AfterClass
		public void tearDown() {
			if (driver != null)
				driver.quit();
		}

	}


