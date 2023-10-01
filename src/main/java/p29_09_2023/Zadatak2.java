package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        /*
        2. Zadatak
        Napisati program koji ucitava stranicu https://youtube.com i u search baru unosi tekste
        Breskvica i ceka da se pojavi vise od 3 rezultata iz padajuceg menija i zatim klikce na prvi.
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://youtube.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       driver.findElement(By.cssSelector("input#search")).click();
       Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#search")).sendKeys("Chase and Status");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("ul.sbsb_b>li"),2));
        driver.findElement(By.cssSelector("ul.sbsb_b>li:first-child")).click();







    }
}
