package dev.jsx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddSeasonPage {
    private WebDriver driver;

    public AddSeasonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getMostRecentSeasonRow() {
        List<WebElement> rows = seasonTableBody.findElements(By.xpath("./tr"));

        return rows.get(rows.size() - 1);
    }

    @FindBy(id="seasonTableBody")
    public WebElement seasonTableBody;

    @FindBy(name="season")
    public WebElement seasonNameInput;

    @FindBy(xpath="/html/body/form/button")
    public WebElement submitButton;

    @FindBy(linkText="Back")
    public WebElement backLink;
}
