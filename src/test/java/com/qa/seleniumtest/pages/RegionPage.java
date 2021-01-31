package com.qa.seleniumtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegionPage {

	// create elements
	@FindBy(id = "regionName")
	private WebElement regionNameForm;
	@FindBy(id = "description")
	private WebElement regionDescriptionForm;
	@FindBy(id = "createRegion")
	private WebElement regionCreate;

	// read Elements
	@FindBy(id = "getRegions")
	private WebElement getRegions;
	@FindBy(id = "readRegionID")
	private WebElement readRegionIDForm;
	@FindBy(id = "getRegionID")
	private WebElement getRegionID;

	// update elements
	@FindBy(id = "upRegionID")
	private WebElement upRegionIDForm;
	@FindBy(id = "upRegionName")
	private WebElement upRegionNameForm;
	@FindBy(id = "upRegionDescription")
	private WebElement upRegionDescForm;
	@FindBy(id = "updateRegion")
	private WebElement upRegion;
	// delete Elements
	@FindBy(id = "deleteRegionID")
	private WebElement deleteRegionID;
	@FindBy(id = "deleteRegionByID")
	private WebElement deleteRegion;

	// status elements
	@FindBy(id = "createStatus")
	private WebElement createStatus;
	@FindBy(id = "regionList")
	private WebElement regionList;
	@FindBy(id = "deleteStatus")
	private WebElement deleteStatus;
	@FindBy(id = "updateStatus")
	private WebElement updateStatus;

	public RegionPage(WebDriver driver) {

	}

	public void create(String name, String desc) {
		regionNameForm.sendKeys(name);
		regionDescriptionForm.sendKeys(desc);
		regionCreate.click();
	}

	public WebElement getCreateStatus() {
		return createStatus;
	}

	public void readAll() {
		this.getRegions.click();
	}

	public void readOne(String id) {
		readRegionIDForm.sendKeys(id);
		getRegionID.click();
	}

	public WebElement getRegionList() {
		return regionList;
	}

	public void update(String id, String name, String desc) {
		upRegionIDForm.sendKeys(id);
		upRegionNameForm.sendKeys(name);
		upRegionDescForm.sendKeys(desc);
		upRegion.click();
	}

	public WebElement getUpdateStatus() {
		return updateStatus;
	}

	public void delete(String id) {
		deleteRegionID.sendKeys(id);
		deleteRegion.click();
	}

	public WebElement getDeleteStatus() {
		return deleteStatus;
	}

}
