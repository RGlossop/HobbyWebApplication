package com.qa.seleniumtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.seleniumtest.pages.HomePage;

public class RegionPageUserTests {

	private static WebDriver driver;
	
	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testCreate() throws InterruptedException {
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navRegions();
		Thread.sleep(500);
		
		website.regionPage.create("Ionia", "Still Stands");
		Thread.sleep(400);
		assertTrue(website.regionPage.getCreateStatus().getText().contains(""));
	}
}
