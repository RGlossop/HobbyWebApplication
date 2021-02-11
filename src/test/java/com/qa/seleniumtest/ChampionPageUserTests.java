package com.qa.seleniumtest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.seleniumtest.pages.HomePage;

public class ChampionPageUserTests {

	private static WebDriver driver;

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testCreate() throws InterruptedException {
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navChamps();
		Thread.sleep(500);
		
		website.champPage.create("Kayn", "Noxus", "1");
		Thread.sleep(400);
		assertTrue(website.champPage.getCreateStatus().getText().contains("Created"));
	}

	@Test
	public void testReadChamp() throws InterruptedException {
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		website.navChamps();
		Thread.sleep(500);

		website.champPage.readChamp("1");
		Thread.sleep(400);
		assertTrue(website.champPage.getChampList().getText().contains("Braum"));
	}

	@Test
	public void testReadChamps() throws InterruptedException {
		HomePage website = PageFactory.initElements(driver, HomePage.class);

		website.navChamps();
		Thread.sleep(500);
		website.champPage.readChamps();
		Thread.sleep(400);
		assertTrue(website.champPage.getChampList().getText().contains("Braum"));
	}

	@Test
	public void testUpdateChamp() throws InterruptedException {
		HomePage website = PageFactory.initElements(driver, HomePage.class);

		website.navChamps();
		Thread.sleep(500);
		
		website.champPage.updateChamp("1", "Braum", "Tank", "1");
		Thread.sleep(400);
		assertTrue(website.champPage.getUpdateStatus().getText().contains("Updated"));
	}
	@Test
	public void testDeleteChamp() throws InterruptedException {
		HomePage website = PageFactory.initElements(driver, HomePage.class);

		website.navChamps();
		Thread.sleep(500);
		
		website.champPage.deleteChamp("3");
		Thread.sleep(400);
		assertTrue(website.champPage.getDeleteStatus().getText().contains("Deleted"));
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
	}

}
