package dev.jsx.steps;

import dev.jsx.pages.AddSeasonPage;
import dev.jsx.pages.ScheduleGamePage;
import dev.jsx.pages.SeasonPage;
import dev.jsx.runners.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.fail;

public class AdminNegativeStepImpl {

    public WebDriver driver = TestRunner.driver;
    public ScheduleGamePage scheduleGamePage = new ScheduleGamePage(driver);
    public SeasonPage seasonPage = new SeasonPage(driver);
    public AddSeasonPage addSeasonPage = new AddSeasonPage(driver);

    //Admin schedules game series with only the defaults
    @Then("User gets a popup of empty fields")
    public void user_gets_a_popup_of_empty_fields() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#popup")));

            String popupText = driver.findElement(By.cssSelector("#popup")).getText();
            if (popupText.equals("Missing value")) {
                // ignore the popup and continue with the test
                driver.findElement(By.cssSelector("#popup .close-button")).click();
            } else {
                fail("Unexpected popup appeared with text: " + popupText);
            }

        } catch (TimeoutException e) {
            fail("The program is timeout.");
        }
    }


    //Admin schedule game series with no date selected
    @Then("User gets a popup of empty date field")
    public void user_gets_a_popup_of_empty_date_field() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#popup")));

            String popupText = driver.findElement(By.cssSelector("#popup")).getText();
            if (popupText.equals("Missing value")) {
                // ignore the popup and continue with the test
                driver.findElement(By.cssSelector("#popup .close-button")).click();
            } else {
                fail("Unexpected popup appeared with text: " + popupText);
            }

        } catch (TimeoutException e) {
            fail("The program is timeout.");
        }
    }


    //Admin schedule game series with a past date selected
    @When("User selects a past date")
    public void user_selects_a_past_date() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(scheduleGamePage.timeInput));

        scheduleGamePage.timeInput.clear();
        Actions actions = new Actions(driver);
        scheduleGamePage.timeInput.sendKeys("01/01/1990");
        actions.sendKeys(Keys.TAB).perform();
        scheduleGamePage.timeInput.sendKeys("0100");
        scheduleGamePage.timeInput.sendKeys("PM");
    }
    @Then("User gets a popup of selecting a future date")
    public void user_gets_a_popup_of_selecting_a_future_date() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#popup")));

            String popupText = driver.findElement(By.cssSelector("#popup")).getText();
            if (popupText.equals("Select New Date")) {
                // ignore the popup and continue with the test
                driver.findElement(By.cssSelector("#popup .close-button")).click();
            } else {
                fail("Unexpected popup appeared with text: " + popupText);
            }

        } catch (TimeoutException e) {
            fail("The program is timeout.");
        }
    }


    //Admin schedule game series with no sports selected
    @Then("User gets a popup of selecting sports field")
    public void user_gets_a_popup_of_selecting_sports_field() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#popup")));

            String popupText = driver.findElement(By.cssSelector("#popup")).getText();
            if (popupText.equals("Missing value")) {
                // ignore the popup and continue with the test
                driver.findElement(By.cssSelector("#popup .close-button")).click();
            } else {
                fail("Unexpected popup appeared with text: " + popupText);
            }

        } catch (TimeoutException e) {
            fail("The program is timeout.");
        }
    }


    //Negative-game-season feature
    @Then("User gets a popup of empty field in season name")
    public void user_gets_a_popup_of_empty_field_in_season_name() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#popup")));

            String popupText = driver.findElement(By.cssSelector("#popup")).getText();
            if (popupText.equals("Missing value")) {
                // ignore the popup and continue with the test
                driver.findElement(By.cssSelector("#popup .close-button")).click();
            } else {
                fail("Unexpected popup appeared with text: " + popupText);
            }

        } catch (TimeoutException e) {
            fail("The program is timeout.");
        }

    }


}
