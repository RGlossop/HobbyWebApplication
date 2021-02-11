package com.qa.seleniumtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChampionPage {

	//create elements
	@FindBy(id = "champName")
	private WebElement champNameForm;
	@FindBy(id = "champRole")
	private WebElement champRoleForm;
	@FindBy(id = "champRegionID")
	private WebElement champRegionForm;
	@FindBy(id = "createChamp")
	private WebElement createChamp;
	
	//read elements
	@FindBy(id = "getChampById")
	private WebElement readOne;
	@FindBy(id = "readChampID")
	private WebElement readOneForm;
	@FindBy(id = "getChamps")
	private WebElement readAll;
	//update elements
	@FindBy(id="upChampID")
	private WebElement upChampID;
	@FindBy(id="upChampName")
	private WebElement upChampName;
	@FindBy(id="upChampRole")
	private WebElement upChampRole;
	@FindBy(id="upChampRegion")
	private WebElement upChampRegion;
	@FindBy(id="updateChamp")
	private WebElement updateChamp;
	//delete Elements
	@FindBy(id="deleteChampID")
	private WebElement deleteIDForm;
	@FindBy(id="deleteChampById")
	private WebElement deleteChamp;

	
	@FindBy(id = "champList")
	private WebElement champList;
	@FindBy(id = "createStatus")
	private WebElement createStatus;
	@FindBy(id="deleteStatus")
	private WebElement deleteStatus;
	@FindBy(id="updateStatus")
	private WebElement updateStatus;
	
	public ChampionPage(WebDriver driver) {
		
	}
	public void create(String name, String role, String id) {
		champNameForm.sendKeys(name);
		champRoleForm.sendKeys(role);
		champRegionForm.sendKeys(id);
		createChamp.click();
	}
	
	public void readChamps() {
		this.readAll.click();
	}
	
	public void readChamp(String id) {
		readOneForm.sendKeys(id);
		readOne.click();
	}
	
	public void updateChamp(String id, String name, String role, String regID) {
		upChampID.sendKeys(id);
		upChampName.sendKeys(name);
		upChampRole.sendKeys(role);
		upChampRegion.sendKeys(regID);
		updateChamp.click();
	}
	
	public void deleteChamp(String id) {
		deleteIDForm.sendKeys(id);
		deleteChamp.click();
	}
	public WebElement getDeleteStatus() {
		return deleteStatus;
	}
	public WebElement getCreateStatus() {
		return createStatus;
	}
	public WebElement getChampList() {
		return champList;
	}
	public WebElement getUpdateStatus() {
		return updateStatus;
	}
}
