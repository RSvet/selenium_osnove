package p03_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class KatalonLoginTests {
    /*
    1.Zadatak
    Kreirati klasu KatalonLoginTests za testove
    Base url: https://cms.demo.katalon.com
    Test #1: Visit login page from Nav bar
    Koraci:
    Ucitati home stranicu
    Kliknuti na My account link
    Verifikovati da je naslov stranice My account – Katalon Shop
    Verifikovati da se u url-u stranice javlja /my-account
    Za sve validacije ispisati odgovarajuce poruke u slucaju greske

    Test #2: Check input types
    Koraci:
    Ucitati /my-account stranicu
    Verifikovati da  polje za unos email-a za atribut type ima vrednost text
    Verifikovati da polje za unos lozinke za atribut type ima vrednost password
    Verifikovati da checkbox Remember me za atribut type ima vrednost checkbox
    Verifikovati da je Remember me checkbox decekiran. Koristan link kako naci informaciu da li je checkbox cekiran ili ne.
    Za sve validacije ispisati odgovarajuce poruke u slucaju greske

    Test #3: Display error when credentials are wrong
    Podaci:
    email: invalidemail@gmail.com
    password: invalid123
    Koraci:
    Ucitati home stranicu
    Kliknuti na My account link
    Unesite email
    Unesite password
    Kliknite na login dugme
    Verifikovati da postoji element koji prikazuje gresku
    Verifikovati da je prikazana greska ERROR: Invalid email address
    Za sve validacije ispisati odgovarajuce poruke u slucaju greske
    Verifikovati da smo idalje na login stranici posle greske, tako sto proveravamo da se url-u javlja /my-account


    Test #4: Successful login with valid credentials
    Podaci:
    username: customer
    password: crz7mrb.KNG3yxv1fbn
    Koraci:
    Ucitati home stranicu
    Kliknuti na My account link
    Unesite email
    Unesite password
    Kliknite na login dugme
    Verifikovati da se pojavljuje link za logout na stranici

     */

    private WebDriver driver;
    private WebDriverWait wait;

    private String baseUrl = "https://cms.demo.katalon.com";

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @BeforeMethod
    public void beforeMethod(){
       // driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
    }

    @Test(priority = 2)
    public void checkInputTypes(){
        driver.findElement(By.cssSelector(".nav-menu>li:nth-child(3)")).click();
        wait    .withMessage("Title should be 'My account-Katalon shop'")
                .until(ExpectedConditions.titleIs("My account – Katalon Shop"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"), "Url should contain '/my account'");
    }

    @Test (priority = 1)
    public void visitLoginPageFromNavBar(){
        driver.findElement(By.cssSelector(".nav-menu>li:nth-child(3)")).click();
        String typeEmail = driver.findElement(By.id("username")).getAttribute("type");
        Assert.assertEquals(typeEmail, "text", "Input type for email should be text");

        String typePassword = driver.findElement(By.id("password")).getAttribute("type");
        Assert.assertEquals(typePassword, "password", "Input type for password should be password");

        String typeCheckbox = driver.findElement(By.id("rememberme")).getAttribute("type");
        Assert.assertEquals(typeCheckbox, "checkbox", "Input type for checkbox should be checkbox");

        Assert.assertTrue(!driver.findElement(By.id("rememberme")).isSelected(), "checkbox is selected");

        driver.findElement(By.cssSelector(".nav-menu>li:nth-child(3)")).click();

        wait    .withMessage("Title should be 'My account-Katalon shop'")
                .until(ExpectedConditions.titleIs("My account – Katalon Shop"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"), "Url should contain '/my account'");
    }

    @Test (priority = 3)
    public void displayErrorWhenCredentialsAreWrong(){
        driver.findElement(By.cssSelector(".nav-menu>li:nth-child(3)")).click();
        driver.findElement(By.id("username")).sendKeys("invalidemail@gmail.com");
        driver.findElement(By.id("password")).sendKeys("invalid123");
        driver.findElement(By.name("login")).click();
        wait
                .withMessage("There should be error container")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("woocommerce-error")));

        Assert.assertTrue(driver.findElement(By.cssSelector(".woocommerce-error>li"))
                .getText().contains("ERROR: Invalid email address"), "There should be text 'ERROR: Invalid email address'");

        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"), "Url should contain /my-account");

    }


    @Test (priority = 4)
    public void successfulLoginWithValidCredentials(){
        driver.findElement(By.cssSelector(".nav-menu>li:nth-child(3)")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.id("password")).sendKeys("crz7mrb.KNG3yxv1fbn");
        driver.findElement(By.name("login")).click();
        wait
                .withMessage("There is no log out link!")
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.linkText("Log out"),0));

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
