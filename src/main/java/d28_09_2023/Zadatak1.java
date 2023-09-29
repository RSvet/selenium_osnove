package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak1 {
    public static void main(String[] args)  {
        /*
        1.Zadatak
●	Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
●	Klik na Type drawdown
●	Klik na Public iz drowdowna
●	Ceka da se Clear dugme u desnom uglu prikaze koristeci explicit wait
●	Kilk na Clear filter u desnom uglu
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=");

        driver.findElement(By.xpath("//*[@id='type-options']")).click();
        driver.findElement(By.cssSelector(".SelectMenu-list>label:nth-child(2)")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#org-repositories a.issues-reset-query"))).click();

        driver.quit();

    }
}
