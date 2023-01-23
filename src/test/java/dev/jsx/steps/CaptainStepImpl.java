package dev.jsx.steps;

import dev.jsx.pages.ApproveOrDenyRequestPage;
import dev.jsx.pages.LoginPage;
import dev.jsx.pages.MainPage;
import dev.jsx.runners.TestRunner;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CaptainStepImpl {

    public WebDriver driver = TestRunner.driver;
    public MainPage mainPage = new MainPage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    public ApproveOrDenyRequestPage requestPage = new ApproveOrDenyRequestPage(driver);

    //view-player features
    @Given("The player logged in is a captain")
    public void the_player_logged_in_is_a_captain() {
        driver.get("http://127.0.0.1:5500/index.html");

        mainPage.loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));

        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys("Candice202");
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys("pass123");

        loginPage.loginButton.click();
    }
    @Given("The player is on home page")
    public void the_player_is_on_home_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Approve/Deny Team Requests")));

        String expected = driver.getTitle();
        String actual = "User Page";

        assertEquals(expected, actual);
    }
    @Given("The player is on View Your Team page")
    public void the_player_is_on_view_your_team_page() {
        WebElement viewTeam = driver.findElement(By.linkText("View Your Team"));

        viewTeam.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"),("Your Team, The Ballers's Members")));

        String expected = driver.getTitle();
        String actual = "Team Application";

        assertEquals(expected, actual);

    }
    @When("The player clicks on see the player")
    public void the_player_clicks_on_see_the_player() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr/td[4]/button")));

        WebElement playerBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[4]/button"));

        playerBtn.click();
    }
    @Then("The player can view details on a player")
    public void the_player_can_view_details_on_a_player() {
        try  {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h2"), ("The Ballers's Captain")));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
    @When("The player clicks on close button")
    public void the_player_clicks_on_close_button() {
        WebElement close = driver.findElement(By.xpath("/html/body/section/button"));

        close.click();
    }
    @When("The player clicks back")
    public void the_player_clicks_back() {
        WebElement backBtn = driver.findElement(By.linkText("Back"));

        backBtn.click();
    }
    @Then("The player is back on the homepage")
    public void the_player_is_back_on_the_homepage() {
        String expected = driver.getTitle();
        String actual = "User Page";

        assertEquals(expected, actual);
    }

    //View players to Accept/Deny
    @Given("The player is on Approve or Deny Team Requests Page")
    public void the_player_is_on_approve_or_deny_team_requests_page() {
        WebElement teamRequests = driver.findElement(By.linkText("Approve/Deny Team Requests"));

        teamRequests.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"),("Team Requests for The Ballers")));

        String expected = driver.getTitle();
        String actual = "Team Requests";

        assertEquals(expected, actual);
    }
    @When("The player can click on See The Player")
    public void the_player_can_click_on_see_the_player() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr[1]/td[6]/button")));

            WebElement seePlayer = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[6]/button"));

            seePlayer.click();
        } catch (TimeoutException e) {
            fail("The program has timeout.");
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr[1]/td[4]/button")));

            WebElement seePlayer = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[4]/button"));

            seePlayer.click();
        } catch (TimeoutException e) {
            fail("The program has timeout.");
        }
    }

    @Then("The player view details on a player")
    public void the_player_view_details_on_a_player() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section/button")));

    }


    //Approve player feature
    @When("The player clicks on approve")
    public void the_player_clicks_on_approve() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr[1]/td[4]/button")));

            WebElement accept = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[4]/button"));

            accept.click();
        } catch (TimeoutException e) {
            fail("The program has timeout.");
        }
    }

    @Then("An alert appears saying the player has been approved")
    public void an_alert_appears_saying_the_player_has_been_approved() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());

            String expectedMessage = "Approve successful";
            String alertMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            assertEquals(expectedMessage, alertMessage);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }



    //Deny player feature
    @When("The player clicks on deny button")
    public void the_player_clicks_on_deny_button() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr[1]/td[5]/button")));

            WebElement deny = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[5]/button"));

            deny.click();
        } catch (TimeoutException e) {
            fail("The program has timeout.");
        }
    }
    @Then("An alert appears saying the player has been denied")
    public void an_alert_appears_saying_the_player_has_been_denied() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());

            String expectedMessage = "Deny successful";
            String alertMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            assertEquals(expectedMessage, alertMessage);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Then("The alert confirmation disappears and the captain sees the request status")
    public void the_alert_confirmation_disappears_and_the_captain_sees_the_request_status() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table/tbody/tr[1]/td[4]/button")));

        } catch (TimeoutException e) {
            fail("The program has timeout.");
        }

        requestPage.backLink.click();
    }


}
