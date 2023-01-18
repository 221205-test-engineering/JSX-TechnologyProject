package dev.jsx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OfficiateGamePage {

    private WebDriver driver;

    public OfficiateGamePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public List<String> getGames() {
        return gameTableBody.findElements(By.xpath("./tr/td"))
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @FindBy(id="gameTableBody")
    public WebElement gameTableBody;


    @FindBy(xpath="//*[@id=\"gameTableBody\"]/tr[1]")
    public WebElement firstRow;


    @FindBy(xpath="//*[@id=\"gameTableBody\"]/tr[1]/td[8]/button[contains(text(),'Update Scores')]")
    public List<WebElement> showCaptainButtonList;


    @FindBy(linkText="Back")
    public WebElement backLink;

    @FindBy(xpath="//*[@id=\"playerLists\"]/button[1]")
    public WebElement closeButton;

    // Home Team Pop up Menu
    //
    @FindBy(xpath ="//*[@id=\"homeTeam\"]/dl/dt")
    public WebElement homeTeamUserID;


    // input points for home team
    @FindBy(xpath="//*[@id=\"homeTeam\"]/dl/dd[1]/input")
    public WebElement homePointInput;

//    Add 1 point
    @FindBy(xpath="//*[@id=\"homeTeam\"]/dl/dd[1]/button[1]")
    public WebElement homeAddOnePointButton;

    // add 2 points
    @FindBy(xpath="//*[@id=\"homeTeam\"]/dl/dd[1]/button[1]")
    public WebElement homeAddTwoPointsButton;

    //add 3 points
    @FindBy(xpath="//*[@id=\"homeTeam\"]/dl/dd[1]/button[1]")
    public WebElement homeAddThreePointsButton;

    // add fouls to home team
    @FindBy(xpath="//*[@id=\"homeTeam\"]/dl/dd[2]/input")
    public WebElement homeFoulsInput;

    // add button for fouls
    @FindBy(xpath="//*[@id=\"homeTeam\"]/dl/dd[2]/button[1]")
    public WebElement addFoulsButton;

    //*[@id="awayTeam"]/dl/dt


    @FindBy(xpath ="//*[@id=\"awayTeam\"]/dl/dt")
    public WebElement awayTeamUserID;


    // input points for home team
    @FindBy(xpath="//*[@id=\"awayTeam\"]/dl/dd[1]/input")
    public WebElement awayPointInput;

    //    Add 1 point
    @FindBy(xpath="//*[@id=\"awayTeam\"]/dl/dd[1]/button[1]")
    public WebElement awayAddOnePointButton;

    // add 2 points
    @FindBy(xpath="//*[@id=\"awayTeam\"]/dl/dd[1]/button[1]")
    public WebElement awayAddTwoPointsButton;

    //add 3 points
    @FindBy(xpath="//*[@id=\"awayTeam\"]/dl/dd[1]/button[1]")
    public WebElement awayAddThreePointsButton;

    // add fouls to home team
    @FindBy(xpath="//*[@id=\"awayTeam\"]/dl/dd[2]/input")
    public WebElement awayFoulsInput;

    // add button for fouls
    @FindBy(xpath="//*[@id=\"awayTeam\"]/dl/dd[2]/button[1]")
    public WebElement awayFoulsButton;

    @FindBy(xpath="//*[@id=\"playerLists\"]/button[2]")
    public WebElement saveButton;



}
