package dev.jsx.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TeamApplicationPage {

    private WebDriver driver;

    public TeamApplicationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // drop down menu element & associated method for choosing dropdown menu value based on string value
    @FindBy(tagName ="select")
    public WebElement teamSelectDropDown;

    public void selectDropDown(String string) {
        Select newSelect = new Select(teamSelectDropDown);
        newSelect.selectByValue(string);
    }


    @FindBy(xpath ="/html/body/div/button[contains(text(),'Apply')]")
    public WebElement applyButton;

    @FindBy(linkText="Back")
    public WebElement backButton;




}
