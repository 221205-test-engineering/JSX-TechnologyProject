package dev.jsx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserManagerPage {
    private WebDriver driver;

    public UserManagerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getRowByUserId(String id) {
        List<WebElement> rows = userTableBody.findElements(By.xpath("./tr"));

        for (int i = 0; i < rows.size(); i++) {
            WebElement userIdCell = rows.get(i).findElement(By.xpath("./td[1]"));

            if (userIdCell.getText() == id) {
                return rows.get(i);
            }
        }

        return null;
    }
    @FindBy(id="userTableBody")
    public WebElement userTableBody;

    @FindBy(linkText="Back")
    public WebElement backLink;
}
