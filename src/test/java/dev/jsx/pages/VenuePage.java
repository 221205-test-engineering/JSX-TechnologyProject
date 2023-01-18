package dev.jsx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class VenuePage {
    private WebDriver driver;

    public VenuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getVenues() {
        return venueTableBody.findElements(By.xpath("./tr/td"))
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @FindBy(id="venueTableBody")
    public WebElement venueTableBody;

    @FindBy(xpath="*[@id='venueTableBody']/tr[1]")
    public WebElement firstRow;

    @FindBy(linkText="Back")
    public WebElement backLink;
}
