package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import p05_10_2023.SwagLabsRetry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        int numberOfItemsBefore = topNavPage.getNumberOfItemsFromCartBadge();

        inventoryPage.clickToAddItemToCart();
        Assert.assertTrue(inventoryPage.checkIfThereIsRemoveButton(), "There is no remove button");

        int numberOfItemsAfter = topNavPage.getNumberOfItemsFromCartBadge();

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
        topNavPage.waitForHamburgerButton();

    }

    @Test(priority = 11, retryAnalyzer = SwagLabsRetry.class)
    public void verifyCartIconIsPresentOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.waitForCartIcon();
    }


    @Test(priority = 12, retryAnalyzer = SwagLabsRetry.class)
    public void verifyHamburgerButtonIsEnabledOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();
        Assert.assertTrue(topNavPage.hamburgerButtonIsEnabled(), "Hamburger button is not enabled");
    }

    @Test(priority = 13, retryAnalyzer = SwagLabsRetry.class)
    public void verifyCartIconIsEnabledOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(topNavPage.cartIconIsEnabled(), "Cart icon is not enabled");
    }

    @Test(priority = 14, retryAnalyzer = SwagLabsRetry.class)
    public void verifyHamburgerButtonIsWorkingOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();
        leftNavPage.waitLeftNavMenu();
    }

    @Test(priority = 15, retryAnalyzer = SwagLabsRetry.class)
    public void verifyCartIconIsWorkingOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl2 + "cart.html",
                "Should be redirected to cart page after login.");
    }

    @Test(priority = 16, retryAnalyzer = SwagLabsRetry.class)
    public void verifyNumberOfAddedItemsToCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();

        int numberOfItemsBefore = topNavPage.getNumberOfItemsFromCartBadge();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(inventoryPage.checkIfThereIsRemoveButton(), "There is no remove button");

        int numberOfItemsAfter = topNavPage.getNumberOfItemsFromCartBadge();

        Assert.assertEquals(numberOfItemsAfter, numberOfItemsBefore+1, "Number of items in the cart did not increase");
    }

    @Test(priority = 17, retryAnalyzer = SwagLabsRetry.class)
    public void verifySubHeaderTitleOnCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(topNavPage.getTextFromSubheaderTitle(), "Your Cart",
                "Subheader title should be Your Cart");
    }

    @Test(priority = 18, retryAnalyzer = SwagLabsRetry.class)
    public void verifyNumberOfMenuOptionInLeftNavigationMenu(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();

        leftNavPage.waitLeftNavMenu();

        Assert.assertEquals(leftNavPage.getNumberOfMenuOptions(), 4,
                "There should be four menu options in left navigation");

    }
    @Test(priority = 19, retryAnalyzer = SwagLabsRetry.class)
    public void verifySpellingOfMenuOptionInLeftNavigationMenu(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();

        leftNavPage.waitLeftNavMenu();

        List<String> correctSpelledMenu = new ArrayList<>(Arrays.asList("All Items", "About", "Logout", "Reset App State"));

        for (int i = 0; i < leftNavPage.getNumberOfMenuOptions(); i++) {
            Assert.assertEquals(leftNavPage.getTextFromMenuOptions().get(i), correctSpelledMenu.get(i),
                    leftNavPage.getTextFromMenuOptions().get(i)+" is not correctly spelled");
        }

    }

    @Test(priority = 20, retryAnalyzer = SwagLabsRetry.class)
    public void verifyAllItemsMenuOptionIsWorking(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();

        leftNavPage.waitLeftNavMenu();

        leftNavPage.clickAllItems();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl2 + "inventory.html",
                "Should be redirected to products page.");

    }

    @Test(priority = 21, retryAnalyzer = SwagLabsRetry.class)
    public void verifyAboutMenuOptionIsWorking(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();

        leftNavPage.waitLeftNavMenu();

        leftNavPage.clickAbout();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://saucelabs.com/",
                "Should be redirected to sauce labs website.");

    }

    @Test(priority = 22, retryAnalyzer = SwagLabsRetry.class)
    public void verifyLogoutMenuOptionIsWorking(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();

        leftNavPage.waitLeftNavMenu();

        leftNavPage.clickLogoutButton();

        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");

    }

    @Test(priority = 23, retryAnalyzer = SwagLabsRetry.class)
    public void verifyResetAppStateMenuOptionIsWorking(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        boolean badgeExists = topNavPage.checkIfCartBadgeExists();

        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();
        leftNavPage.waitLeftNavMenu();
        leftNavPage.clickResetAppState();

        boolean badgeExistsAfterReset = topNavPage.checkIfCartBadgeExists();

        Assert.assertEquals(badgeExistsAfterReset, !badgeExists, "Reset option is not resetting the app");

    }

    @Test(priority = 24, retryAnalyzer = SwagLabsRetry.class)
    public void verifyXButtonIsPresentOnLeftNav(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();
        leftNavPage.waitXButton();

    }

    @Test(priority = 25, retryAnalyzer = SwagLabsRetry.class)
    public void verifyXButtonIsWorkingOnLeftNav(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburger();
        leftNavPage.waitXButton();
        leftNavPage.clickXButton();
        leftNavPage.waitLeftNavMenuDissapear();
    }

    @Test(priority = 26, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAddedItemIsPresentOnTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        String nameOfAddedItem = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.getTextFromAddedItemName(), nameOfAddedItem,
                "Item added is not the same as item in the cart");
    }

    @Test(priority = 27, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAddedItemTitleIsPresented(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemTitle();
    }

    @Test(priority = 28, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAddedItemDescriptionIsPresented(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemDescription();
    }

    @Test(priority = 29, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAddedItemPriceIsPresented(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemPrice();
    }

    @Test(priority = 30, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfAddedItemQuantityIsPresented(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemQuantity();
    }

    @Test(priority = 31, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTitleOfAddedItemIsClickable(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemTitleToBeClickable();
    }

    @Test(priority = 32, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTitleOfAddedItemIsWorking(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemTitleToBeClickable();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"),"User is not on the item page");
    }

    @Test(priority = 33, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfRemoveButtonIsVisibleInTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForRemoveButtonToBeVisible();
    }

    @Test(priority = 34, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfRemoveButtonIsWorkingInTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();
        cartPage.clickOnRemoveButton();

        Assert.assertFalse(cartPage.checkIfAddedItemsExist(), "Added item is not removed");
    }
    @Test(priority = 35, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfContinueShoppingButtonIsPresentedInTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();

        cartPage.waitForContinueShoppingButtonToBeVisible();

    }

    @Test(priority = 36, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfContinueShoppingButtonIsWorkingInTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();

        cartPage.waitForContinueShoppingButtonToBeVisible();
        cartPage.clickContinueShoppingButton();

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl2+"inventory.html");

    }

    @Test(priority = 37, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfCheckoutButtonIsVisibleInTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();

        cartPage.waitForCheckoutButtonToBeVisible();

    }

    @Test(priority = 38, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfCheckoutButtonIsWorkingInTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        inventoryPage.clickToAddItemToCart();
        topNavPage.clickOnCartButton();

        cartPage.waitForCheckoutButtonToBeVisible();
        cartPage.clickCheckoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl2+"checkout-step-one.html",
                "User is not redirected to checkout page");
    }

    @Test(priority = 39, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTwitterButtonIsPresentedInTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();
        footerPage.waitForTwitterIconToBeVisible();
    }

    @Test(priority = 40, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfFacebookButtonIsPresentedInTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();
        footerPage.waitForFacebookIconToBeVisible();
    }

    @Test(priority = 41, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfLinkedinButtonIsPresentedInTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();
        footerPage.waitForLinkedinIconToBeVisible();
    }

    @Test(priority = 42, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfTwitterButtonWorksInTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();
        footerPage.waitForTwitterIconToBeVisible();
        footerPage.clickOnTwitterIcon();
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs .get(1));
        wait
                .withMessage("User is not redirected to twitter page")
                .until(ExpectedConditions.urlToBe("https://twitter.com/saucelabs"));
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
    }

    @Test(priority = 43, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfFacebookButtonWorksInTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();
        footerPage.waitForFacebookIconToBeVisible();
        footerPage.clickOnFacebookIcon();

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs .get(1));
        wait
                .withMessage("User is not redirected to facebook page")
                .until(ExpectedConditions.urlToBe("https://www.facebook.com/saucelabs"));
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
    }

    @Test(priority = 44, retryAnalyzer = SwagLabsRetry.class)
    public void verifyIfLinkedinButtonWorksInTheCartPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();
        footerPage.waitForFacebookIconToBeVisible();
        footerPage.clickOnLinkedinIcon();

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs .get(1));
        wait
                .withMessage("User is not redirected to linkedin page")
                .until(ExpectedConditions.urlToBe("https://www.linkedin.com/company/sauce-labs/"));
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
    }

    @Test(priority = 45, retryAnalyzer = SwagLabsRetry.class)
    public void verifyCopyrightNoticeMessage(){
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.clickOnLoginButton();
        topNavPage.clickOnCartButton();
        footerPage.scrollToFooter();

        Assert.assertEquals(footerPage.getTextFromCopyrightElement(),
                "© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy",
                "The copyright text in the footer is not correct");

    }















}
