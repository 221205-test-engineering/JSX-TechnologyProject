package dev.jsx.steps.CreateOrManageAccount;

import dev.jsx.pages.LoginPage;
import dev.jsx.pages.MainPage;
import dev.jsx.pages.OptionPage;
import dev.jsx.runners.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

import static dev.jsx.runners.TestRunner.driver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPositiveStepImpl {

    public static WebDriver driver = TestRunner.driver;
    MainPage mainPage = new MainPage(driver);

    LoginPage loginPage = new LoginPage(driver);

    OptionPage optionPage = new OptionPage(driver);


    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://127.0.0.1:5500/index.html");

        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))     //create wait to wait until element is visible
                .pollingEvery(Duration.ofMillis(140))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.visibilityOf(mainPage.loginLink)).click();
        }


    @When("The user types {string} into username input")
    public void the_user_types_into_username_input(String string) {
        // Write code here that turns the phrase above into concrete actions
//        System.out.println(string);
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))     //create wait to wait until element is visible
                .pollingEvery(Duration.ofMillis(140))
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.visibilityOf(loginPage.usernameInput));

        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(string);
    }

    @When("The user types {string} into password input")
    public void the_user_types_into_password_input(String string) {
        // Write code here that turns the phrase above into concrete actions
//        System.out.println(string);
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(string);
    }

    @When("The user clicks on the login button")
    public void the_user_clicks_on_the_login_button() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        loginPage.loginButton.click();
//        System.out.println("Login Page Button clicked.");
    }

    @Then("the user should be on the User Page")
    public void the_user_should_be_on_the_user_page() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .elementToBeClickable(optionPage.editUserProfileButton));

        String title = driver.getTitle();
//        System.out.println(title);
        assertEquals("User Page",title);
    }

    @Then("The user should see their name {string} on the home page")
    public void the_user_should_see_their_name_on_the_home_page(String string) {
        // Write code here that turns the phrase above into concrete actions

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(optionPage.usernameTextInfo));
        String userName = optionPage.usernameTextInfo.getText();
        String welcomeString = "Welcome, " + string + "!";
        assertEquals(userName, welcomeString);
    }


    @Then("The user should see their role {string} on the home page")
    public void the_user_should_see_their_role_on_the_home_page(String string) {
        // Write code here that turns the phrase above into concrete actions

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(optionPage.roleTextInfo));
        String userRole = optionPage.roleTextInfo.getText();
        String STwo = string + " Page";
        assertEquals(userRole, STwo);
    }


}
