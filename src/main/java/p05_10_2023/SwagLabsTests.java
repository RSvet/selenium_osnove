package p05_10_2023;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import p02_10_2023.Helper;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class SwagLabsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    private JavascriptExecutor js;


    private String baseUrl = "https://www.saucedemo.com/";

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.manage().deleteAllCookies();

        driver.navigate().to(baseUrl);
    }

    @Test (priority = 1, retryAnalyzer = SwagLabsRetry.class )
    public void verifyErrorIsDisplayedWhenUsernameIsMissing(){
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("The message should be: Epic sadface: Username is required")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h3[data-test='error']"),"Epic sadface: Username is required"));
      }

    @Test
    public void verifyErrorIsDisplayedWhenPasswordIsMissing(){
        driver.findElement(By.id("user-name")).sendKeys("blabla");
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("The message should be: Epic sadface: Password is required")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h3[data-test='error']"),"Epic sadface: Password is required"));

    }
    @Test
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong(){
        String username= "standard_user";
        String password = "invalidpassword";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("The message should be: Epic sadface: Username and password do not match any user in this service")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h3[data-test='error']"),
                        "Epic sadface: Username and password do not match any user in this service"));

    }

    @Test
    public void verifyErrorIsDisplayedWhenUserIsLocked(){
        String username= "locked_out_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h3[data-test='error']")).getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "The message should be: Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void verifySuccessfulLogin(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();


        Assert.assertEquals(driver.getCurrentUrl(), baseUrl+"inventory.html", "Should be redirected to inventory page after login");
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait
                .withMessage("Side menu is not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));

//        driver.findElement(By.cssSelector("a#logout_sidebar_link")).click();
//        wait
//                .withMessage("Login form is not visible after logout")
//                .until(ExpectedConditions.presenceOfElementLocated(By.className("login_container")));

        boolean logoutExists =
                !driver.findElements(By.id("logout_sidebar_link")).isEmpty();

        Assert.assertTrue(logoutExists, "Logout should exists.");

        driver.findElement(By.id("logout_sidebar_link")).click();

        boolean loginFormExists =
                !driver.findElements(By.className("login_wrapper")).isEmpty();

        Assert.assertTrue(
                loginFormExists,
                "Should be redirected to login page after logout.");
    }

    @Test
    public void addingProductsToCart(){

        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "Url should contain  /inventory.html");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        wait
                .withMessage("Remove button is not visible")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));

        int initialCartNumber=0;
        Assert.assertEquals(Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText()), initialCartNumber+1,
                "The number of items in the cart did not change!");

    }
    @Test
    public void viewingProductDetails(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "Url should contain  /inventory.html");

        wait
                .withMessage("Sauce Backpack is not on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sauce Labs Backpack']"))).click();

        wait
                .withMessage("There is no inventory details")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_desc_container")));

       Assert.assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed(), "There is no inventory name");
       Assert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed(), "There is no inventory description");
       Assert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed(), "There is no inventory price");
       Assert.assertTrue(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed());
    }

    @Test (priority = 6)
    public void removingProductsFromCart(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "Url should contain  /inventory.html");

        wait
                .withMessage("Sauce Backpack is not on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sauce Labs Backpack']")));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("The number of items in the cart is not changed")
                .until(ExpectedConditions.textToBe(By.className("shopping_cart_badge"),"1"));

        driver.findElement(By.className("shopping_cart_link")).click();

        wait
                .withMessage("Item 'Sauce Labs Backpack' is not present in the cart")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.className("inventory_item_name"), "Sauce Labs Backpack"));

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        wait
                .withMessage("Item 'Sauce Labs Backpack' is not deleted from the cart")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[text()='Sauce Labs Backpack']"),0));

    }

    @Test
    public void productCheckout(){
        String username = "standard_user";
        String password = "secret_sauce";

        String checkoutName = "Pera";
        String checkoutLastName = "Peric";
        String checkoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Sauce Backpack is not on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sauce Labs Backpack']")));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("The number of items in the cart is not changed")
                .until(ExpectedConditions.textToBe(By.className("shopping_cart_badge"),"1"));

        driver.findElement(By.className("shopping_cart_link")).click();

        wait
                .withMessage("Cart page did not open")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout"))).click();

        wait
                .withMessage("Checkout form is not present")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_info_container")));

        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);
        driver.findElement(By.id("continue")).click();

        wait
                .withMessage("Checkout overview is not visible on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_summary_container")));

        Assert.assertEquals(driver.findElement(By.className("cart_quantity")).getText(),"1", "Quantity is not 1");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),"Sauce Labs Backpack", "There is no Sauce Labs item");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_price")).getText(), "$29.99", "Price is not 29.99");

        Assert.assertEquals(driver.findElement(By.className("summary_subtotal_label")).getText(), "Item total: $29.99", "Subtotal is not 29.99");

        driver.findElement(By.id("finish")).click();
        wait
                .withMessage("There is no thank you screen")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("complete-header")));

       Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(), "Thank you for your order!", "There is no Thank you message");

    }

    // DOMACI
    @Test
    public void validateSocialLinksInFooter() throws IOException {
        String username = "standard_user";
        String password = "secret_sauce";
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl+"inventory.html", "Should be redirected to inventory page after login");
        WebElement footer = driver.findElement(By.className("footer"));
        new Actions(driver)
                .scrollToElement(footer)
                .perform();

        String twitterUrl = driver.findElement(By.cssSelector(".social_twitter>a")).getAttribute("href");
        String facebookUrl=driver.findElement(By.cssSelector(".social_facebook>a")).getAttribute("href");
        String linkedinUrl = driver.findElement(By.cssSelector(".social_linkedin>a")).getAttribute("href");

        int statusFacebook = Helper.getHTTPResponseStatusCode(facebookUrl);
        int statusLinkedin = Helper.getHTTPResponseStatusCode(linkedinUrl);
        int statusTwitter = Helper.getHTTPResponseStatusCode(twitterUrl);
        System.out.println(statusFacebook);

        Assert.assertEquals(statusFacebook, 200, "Status code for Facebook is not 200");
        Assert.assertEquals(statusTwitter, 200, "Status code for Twitter is not 200");
        Assert.assertEquals(statusLinkedin, 200, "Status code for Linkedin is not 200");
    }

    @Test
    public void testDefaultNameSort() {
        String username = "standard_user";
        String password = "secret_sauce";
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl+"inventory.html",
                "Should be redirected to inventory page after login");
        List<WebElement>productNames = driver.findElements(By.className("inventory_item_name"));
        String previous = "";

        for (int i = 0; i < productNames.size(); i++) {
            Assert.assertFalse(productNames.get(i).getText().compareTo(previous)<0,
                    "Products are not in alphabetical order");
            previous = productNames.get(i).getText();
        }
    }

    @Test
    public void testInvertNamedSort(){
        String username = "standard_user";
        String password = "secret_sauce";
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl+"inventory.html",
                "Should be redirected to inventory page after login");

        Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("za");

        List<WebElement>productNames = driver.findElements(By.className("inventory_item_name"));
        String previous=productNames.get(0).getText();
        for (int i = 0; i < productNames.size(); i++) {
            Assert.assertFalse(productNames.get(i).getText().compareTo(previous)>0,
                    "Products are not in reverse alphabetical order");
            previous = productNames.get(i).getText();
        }
    }

    @Test
    public void testSortPriceLowHigh(){

        String username = "standard_user";
        String password = "secret_sauce";
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl+"inventory.html",
                "Should be redirected to inventory page after login");
        Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        sortDropdown.selectByValue("lohi");

        List<WebElement>productPrices = driver.findElements(By.className("inventory_item_price"));
        double previous = 0;
        for (int i = 0; i < productPrices.size(); i++) {
            double price = Double.parseDouble(productPrices.get(i).getText().substring(1));
            Assert.assertTrue(price>=previous,"Prices are not low to high");
            previous = price;
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        js.executeScript("window.localStorage.clear();");
        if (testResult.getStatus() == ITestResult.FAILURE){
            Helper.takeAScreenshot(driver, "screenshots/"+testResult.getName()+".jpg" );
        }

    }

    @AfterClass
  public void afterClass(){
        driver.quit();
    }



}
