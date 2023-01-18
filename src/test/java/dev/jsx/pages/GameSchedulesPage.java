package dev.jsx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameSchedulesPage {
    private WebDriver driver;

    public GameSchedulesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getGameIds() {
        return gameTableBody.findElements(By.xpath("./tr/td[1]"))
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @FindBy(id="gameTableBody")
    public WebElement gameTableBody;

    @FindBy(xpath="*[@id='gameTableBody']/tr[1]")
    public WebElement firstRow;

    @FindBy(linkText="Back")
    public WebElement backLink;
}
