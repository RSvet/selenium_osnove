package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak1 {
    public static void main(String[] args) {
        /*

        // UPLOAD FAJLA - PRIMER
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.navigate().to("https://demo.guru99.com/test/upload/");
//        File uploadFile = new File("test_data/puppies.jpg");
//        inputFile.sendKeys( uploadFile.getAbsolutePath());

        WebElement inputFile = driver.findElement(By.id("uploadfile_0"));

        inputFile.sendKeys(new File("test_data/puppies.jpg").getAbsolutePath());
        driver.findElement(By.id("submitbutton")).click();
        WebElement res = driver.findElement(By.id("res"));

        wait
                .withMessage("File is not uploaded")
                .until(ExpectedConditions.textToBePresentInElement(res, "successfully uploaded"));
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        driver.navigate().to("https://tus.io/demo");
        WebElement inputElement = driver.findElement(By.cssSelector("#P0-0"));
        new Actions(driver)
                .scrollToElement(inputElement)
                .perform();
        inputElement.sendKeys(new File("test_data/puppies.jpg").getAbsolutePath());
       wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("._buttons_gq6c0_28>a")));
        System.out.println("Download button appeared!");


    }
}
