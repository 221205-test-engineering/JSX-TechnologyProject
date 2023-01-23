package dev.jsx.steps;

import dev.jsx.pages.LoginPage;
import dev.jsx.pages.MainPage;
import dev.jsx.pages.OptionPage;
import dev.jsx.runners.TestRunner;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class user_statsStepImpl {

    public WebDriver driver = TestRunner.driver;
    public MainPage mainPage = new MainPage(driver);
    public LoginPage loginPage = new LoginPage(driver);

    public OptionPage optionPage = new OptionPage(driver);

    @Given("The user is on login page")
    public void the_user_is_on_login_page() {
        driver.get("http://127.0.0.1:5500/index.html");

        mainPage.loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));

        String expected = "Login";
        String actual = driver.getTitle();

        assertEquals(expected,actual);
    }

    @When("The employee clicks on the login button")
    public void the_employee_clicks_on_the_login_button() {

        loginPage.loginButton.click();
    }
    @Then("User is on homepage")
    public void user_is_on_homepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(optionPage.gamesLink));

        String expected = "User Page";
        String actual = driver.getTitle();

        assertEquals(expected, actual);
    }
    @When("User clicks on Games")
    public void user_clicks_on_games() {
        optionPage.gamesLink.click();
    }
    @Then("User is on Game Scheduled page with stats")
    public void user_is_on_game_scheduled_page_with_stats() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr[3]/td[1]")));

        String expected = "Game Schedules";
        String actual = driver.getTitle();
    }
    @When("User click on show Referee")
    public void user_click_on_show_referee() {
        WebElement showRef = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[12]/button"));

        showRef.click();
    }
    @Then("User can see the Referee for that game")
    public void user_can_see_the_referee_for_that_game() {
        WebElement close = driver.findElement(By.xpath("/html/body/section[1]/button"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(close));

        close.click();

        WebElement backLink = driver.findElement(By.linkText("Back"));
        backLink.click();
    }

}
