package dev.jsx.steps;

import dev.jsx.pages.*;
import dev.jsx.runners.TestRunner;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminStepImpl {

    public WebDriver driver = TestRunner.driver;
    public MainPage mainPage = new MainPage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    public ScheduleGamePage scheduleGamePage = new ScheduleGamePage(driver);
    public SeasonPage seasonPage = new SeasonPage(driver);

    public AddSeasonPage addSeasonPage = new AddSeasonPage(driver);
    public UserManagerPage userManagerPage = new UserManagerPage(driver);

    //Schedule-game feature
    @Given("The user is logged in as admin")
    public void the_user_is_logged_in_as_admin() {
        driver.get("http://127.0.0.1:5500/index.html");

        mainPage.loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));

        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys("gatorFan99");
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys("chomp!!");

        loginPage.loginButton.click();
    }
    @Given("The user is on homepage")
    public void the_user_is_on_homepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"),("Admin Page")));

        String expected = driver.getTitle();
        String actual = "User Page";

        assertEquals(expected, actual);

    }
    @When("The user clicks on Schedule game")
    public void the_user_clicks_on_schedule_game() {
        WebElement schedule = driver.findElement(By.linkText("Schedule Game"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(schedule));

        schedule.click();
    }
    @Then("The user is on Game Scheduling Form")
    public void the_user_is_on_game_scheduling_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(scheduleGamePage.backButton));

        String expected = driver.getTitle();
        String actual = "Game Scheduling Form";

        assertEquals(expected, actual);
    }
    @When("User selects a venue")
    public void user_selects_a_venue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(scheduleGamePage.venueDropDown, "Main Campus Gym: Court 1"));

        scheduleGamePage.venueDropDown.sendKeys("Satellite Campus Gym: Smith Field");

    }
    @When("User selects a time and date")
    public void user_selects_a_time_and_date() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(scheduleGamePage.timeInput));

        scheduleGamePage.timeInput.clear();
        Actions actions = new Actions(driver);
        scheduleGamePage.timeInput.sendKeys("05/27/2023");
        actions.sendKeys(Keys.TAB).perform();
        scheduleGamePage.timeInput.sendKeys("0600");
        scheduleGamePage.timeInput.sendKeys("PM");

    }
    @When("User selects a season basketball")
    public void user_selects_a_season_basketball() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(scheduleGamePage.seasonDropDown));
        scheduleGamePage.seasonDropDown.sendKeys("Fall 2022 Regular Season Basketball");
    }
    @When("User selects Sport as {string}")
    public void user_selects_sport_as(String sport) {
        scheduleGamePage.sportDropDown.sendKeys(sport);
    }
    @When("User selects a Home Team")
    public void user_selects_a_home_team() {
       scheduleGamePage.homeSelect.sendKeys("Grand Dunk Railroad");
    }
    @When("User selects an Away Team")
    public void user_selects_an_away_team() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(scheduleGamePage.awaySelect, "Grand Dunk Railroad"));
        scheduleGamePage.awaySelect.sendKeys("The Splash");
    }
    @When("User clicks schedule button")
    public void user_clicks_schedule_button() throws InterruptedException {

        scheduleGamePage.scheduleButton.click();
        Thread.sleep(1000);
    }
    @Then("The admin has scheduled a game")
    public void the_admin_has_scheduled_a_game() {
        // Write code here that turns the phrase above into concrete actions
        scheduleGamePage.backButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"),("Admin Page")));

        String expected = driver.getTitle();
        String actual = "User Page";

        assertEquals(expected, actual);
    }


    //game-season feature
    @When("The user clicks on Add Season")
    public void the_user_clicks_on_add_season() {
        WebElement season = driver.findElement(By.linkText("Add Season"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(season));

        season.click();
    }
    @Then("The user is on Season Scheduler")
    public void the_user_is_on_season_scheduler() throws InterruptedException {
        Thread.sleep(1000);

        String expected = driver.getTitle();
        String actual = "Season Scheduler";

        assertEquals(expected, actual);
    }
    @When("User types {string} in season name")
    public void user_types_in_season_name(String season) {
        addSeasonPage.seasonNameInput.clear();
        addSeasonPage.seasonNameInput.sendKeys(season);
    }
    @When("User clicks on submit button")
    public void user_clicks_on_submit_button() {
        addSeasonPage.submitButton.click();
        //If the season is already added (with same case sensitivity), it won't add it as new/recent.
        //Which is why it doesn't show up on the list below
    }
    @Then("The new season has been added")
    public void the_new_season_has_been_added() throws InterruptedException {
        Thread.sleep(1000);

        String expected = addSeasonPage.getMostRecentSeasonRow().getText();
        String actual = "Winter";

        assertTrue(expected.contains(actual));

    }

    @When("User clicks back link")
    public void user_clicks_back_link() {
        addSeasonPage.backLink.click();
    }

    @Then("User returns to homepage")
    public void user_returns_to_homepage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"),("Admin Page")));

        String expected = driver.getTitle();
        String actual = "User Page";

        assertEquals(expected, actual);
    }

    @When("User clicks on View Season")
    public void user_clicks_on_view_season() {
        WebElement viewSeasons = driver.findElement(By.linkText("View Seasons"));

        viewSeasons.click();
    }

    @Then("The user is on Season page and can view the seasons.")
    public void the_user_is_on_season_page_and_can_view_the_seasons() throws InterruptedException {
        Thread.sleep(2000);

        String expected = driver.getTitle();
        String actual = "Season";

        assertEquals(expected, actual);

        seasonPage.backLink.click();
    }

    //Admin promotes a player as referee
    @When("User clicks on Manage User Roles")
    public void user_clicks_on_manage_user_roles() {
        WebElement manageRoles = driver.findElement(By.linkText("Manage User Roles"));

        manageRoles.click();
    }
    @Then("User is on Manager View page")
    public void user_is_on_manager_view_page(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userTableBody\"]/tr[1]/td[4]/button")));


        String expected = driver.getTitle();
        String actual = "Manager View";

        assertEquals(expected, actual);
    }
    @When("User clicks on a Promote to Referee on a player")
    public void user_clicks_on_a_promote_to_referee_on_a_player() {
        WebElement promoteRef = driver.findElement(By.xpath("//*[@id=\"userTableBody\"]/tr[15]/td[5]/button"));

        promoteRef.click();
    }
    @Then("The page updates and the player role is updated")
    public void the_page_updates_and_the_player_role_is_updated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userTableBody\"]/tr[1]/td[4]/button")));
    }

    //Admin promotes a player as admin
    @When("User clicks on a Promote to Admin on a player")
    public void user_clicks_on_a_promote_to_admin_on_a_player() {
        WebElement promoteAdmin = driver.findElement(By.xpath("//*[@id=\"userTableBody\"]/tr[14]/td[4]/button"));

        promoteAdmin.click();
    }

    //Admin demotes a player
    @When("User clicks on Demote to Player")
    public void user_clicks_on_demote_to_player() {
        WebElement demote1 = driver.findElement(By.xpath("//*[@id=\"userTableBody\"]/tr[14]/td[4]/button"));
        WebElement demote2 = driver.findElement(By.xpath("//*[@id=\"userTableBody\"]/tr[15]/td[4]/button"));

        demote1.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userTableBody\"]/tr[1]/td[4]/button")));

        demote2.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userTableBody\"]/tr[1]/td[4]/button")));

    }


}
