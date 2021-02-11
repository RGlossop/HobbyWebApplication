package com.qa.seleniumtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private final String URL = "http://localhost:8080/index.html";
	@FindBy(id = "navChamps")
	private WebElement navChampionPage;
	@FindBy(id = "navRegions")
	private WebElement navRegionPage;
	
	public ChampionPage champPage;
	public RegionPage regionPage;
	
	public HomePage(WebDriver driver) {
		driver.get(URL);
		champPage = PageFactory.initElements(driver, ChampionPage.class);
		regionPage = PageFactory.initElements(driver, RegionPage.class);
	}
	
	public void navChamps() {
		navChampionPage.click();
	}
	
	public void navRegions() {
		navRegionPage.click();
	}
}
