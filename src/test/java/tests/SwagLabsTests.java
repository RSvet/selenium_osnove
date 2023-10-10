package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import p05_10_2023.SwagLabsRetry;


public class SwagLabsTests extends BasicTest{

    @Test(priority = 1, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing(){
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Username is required",
                "Error message is not valid when username is missing");

    }

    @Test(priority = 2, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing(){
        String username = "standard_user";
        loginPage.fillUsername(username);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Password is required",
                "Error message is not valid when password is missing");
    }

    @Test(priority=3, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong(){
        String username = "standard_user";
        String password = "invalidpassword";

        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is not valid when credentials are wrong.");
    }

    @Test(priority=4, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUserIsLocked(){
        String username = "locked_out_user";
        String password = "secret_sauce";

        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                loginPage.getErrorMessageText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message is not valid when user is locked out.");
    }

    @Test (priority = 5, retryAnalyzer = SwagLabsRetry.class)
    public void verifySuccessfulLogin(){
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl2 + "inventory.html",
                "Should be redirected to inventory page after login.");


        topNavPage.clickOnHamburger();

        leftNavPage.waitLeftNavMenu();

        Assert.assertTrue(leftNavPage.doesLogoutButtonExist(),
                "Logout link should exist on menu.");

        leftNavPage.clickLogoutButton();

        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");
    }

    @Test(priority = 6, retryAnalyzer = SwagLabsRetry.class)
    public void addingProductsToCart(){
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl2 + "inventory.html",
                "Should be redirected to inventory page after login.");

        Assert.assertTrue(inventoryPage.locateSauceLabsItem(), "There is no Sauce Labs Backpack");
        int numberOfItemsBefore = inventoryPage.getNumberOfItemsFromCartBadge();

        inventoryPage.clickToAddItemToCart();
        Assert.assertTrue(inventoryPage.checkIfThereIsRemoveButton(), "There is no remove button");

        int numberOfItemsAfter = inventoryPage.getNumberOfItemsFromCartBadge();

        Assert.assertEquals(numberOfItemsAfter, numberOfItemsBefore+1, "Number of items in the cart did not increase");


    }

    // DOMACI - TESTOVI ZA CART PAGE

   @Test(priority = 7, retryAnalyzer = SwagLabsRetry.class)
    public void verifyUrlOfCartPage(){
       String username = "standard_user";
       String password = "secret_sauce";
       loginPage.fillUsername(username);
       loginPage.fillPassword(password);
       loginPage.clickOnLoginButton();

       topNavPage.clickOnCartButton();

       Assert.assertEquals(
               driver.getCurrentUrl(),
               baseUrl2 + "cart.html",
               "User should be on the cart page!");
   }

    @Test(priority = 8, retryAnalyzer = SwagLabsRetry.class)
    public void verifyPageTitleOfCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertEquals(
                driver.getTitle(),
                "Swag Labs",
                "Page title should be Swag Labs");
    }

    @Test(priority = 9, retryAnalyzer = SwagLabsRetry.class)
    public void verifyHeaderTitleOfCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        Assert.assertEquals(
                topNavPage.getTextFromHeaderTitle(),
                "Swag Labs",
                "Header title should be Swag Labs");
    }

    @Test(priority = 10, retryAnalyzer = SwagLabsRetry.class)
    public void verifyHamburgerButtonIsPresentOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertTrue(topNavPage.doesHamburgerMenuButtonExists(), "Hamburger menu button does not exist");

    }

    @Test(priority = 11, retryAnalyzer = SwagLabsRetry.class)
    public void verifyCartIconIsPresentOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(topNavPage.doesCartIconExists(), "Cart icon does not exist");
    }


    @Test(priority = 12, retryAnalyzer = SwagLabsRetry.class)
    public void verifyHamburgerButtonIsEnabledOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(topNavPage.hamburgerButtonIsEnabled(), "Hamburger button is not enabled");
    }










}
