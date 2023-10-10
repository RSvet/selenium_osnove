package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import p02_10_2023.Helper;
import pages.*;

import java.io.IOException;
import java.time.Duration;

public abstract class BasicTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected JavascriptExecutor js;

    protected UpdateDialogPage updateDialogPage;

    protected DeleteDialogPage deleteDialogPage;

    protected InventoryPage inventoryPage;


   protected String baseUrl = "https://s.bootsnipp.com/iframe/K5yrx";
   protected String baseUrl2 = "https://www.saucedemo.com/";

    protected TablePage tablePage;
    protected LoginPage loginPage;

    protected TopNavPage topNavPage;

    protected LeftNavPage leftNavPage;
    protected CartPage cartPage;

    protected FooterPage footerPage;


    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        tablePage = new TablePage(driver, wait);
        updateDialogPage = new UpdateDialogPage(driver,wait);
        deleteDialogPage = new DeleteDialogPage(driver,wait);
        loginPage = new LoginPage(driver,wait);
        topNavPage = new TopNavPage(driver,wait);
        leftNavPage = new LeftNavPage(driver,wait);
        inventoryPage = new InventoryPage(driver,wait);
        cartPage = new CartPage(driver, wait);
        footerPage = new FooterPage(driver,wait);

    }

    @BeforeMethod
    public void beforeMethod(){
        driver.manage().deleteAllCookies();

        driver.navigate().to(baseUrl2);
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
