package com.sqa.am.drivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BasicTest {

	private boolean acceptNextAlert = true;

	private String baseUrl;

	private WebDriver driver;

	private StringBuffer verificationErrors = new StringBuffer();

	@DataProvider
	public Object[][] anData() {
		// TODO Add A Valid DataProvider for Data Driven Testing
		return new Object[][] { new Object[] { "Test A", "qa selenium" }, new Object[] { "Test B", "java junior" },
				new Object[] { "Test C", "programmer junior" }, new Object[] { "Test D", "programmer junior" } };
	}

	@Test(groups = "basic-test", dataProvider = "anData")
	public void basicTest(String testName, String keywords) throws Exception {
		System.out.println("Current Case:" + testName);
		// TODO Implement Core Test Here
		this.baseUrl = "http://craigslist.com";
		this.driver.get(this.baseUrl + "/");
		this.driver.findElement(By.cssSelector("a.jjj > span.txt")).click();
		this.driver.findElement(By.id("query")).clear();
		this.driver.findElement(By.id("query")).sendKeys(keywords);
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		List<WebElement> jobs = this.driver.findElements(By.cssSelector("p.row > a.i"));
		System.out.println("Keywords: " + keywords);
		for (WebElement job : jobs) {
			System.out.println(job.getAttribute("href"));
		}
		System.out.println("PageSource for " + this.driver.getTitle() + " : " + this.driver.getCurrentUrl());
		System.out.println(this.driver.getPageSource());
	}

	@BeforeClass(groups = "chrome", enabled = false)
	public void setUpChrome() throws Exception {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(groups = "firefox")
	public void setUpFirefox() throws Exception {
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(groups = "ie", enabled = false)
	public void setUpIE() throws Exception {
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		this.driver = new InternetExplorerDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(groups = "safari", enabled = false)
	public void setUpSafari() throws Exception {
		this.driver = new SafariDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun = true, enabled = false)
	public void tearDown() throws Exception {
		this.driver.quit();
		String verificationErrorString = this.verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
	// ALERT HANDLING / ELEMENT FOUND HANDLING
	//
	// private String closeAlertAndGetItsText() {
	// try {
	// Alert alert = this.driver.switchTo().alert();
	// String alertText = alert.getText();
	// if (this.acceptNextAlert) {
	// alert.accept();
	// } else {
	// alert.dismiss();
	// }
	// return alertText;
	// } finally {
	// this.acceptNextAlert = true;
	// }
	// }
	//
	// private boolean isAlertPresent() {
	// try {
	// this.driver.switchTo().alert();
	// return true;
	// } catch (NoAlertPresentException e) {
	// return false;
	// }
	// }
	//
	// private boolean isElementPresent(By by) {
	// try {
	// this.driver.findElement(by);
	// return true;
	// } catch (NoSuchElementException e) {
	// return false;
	// }
	// }
}
