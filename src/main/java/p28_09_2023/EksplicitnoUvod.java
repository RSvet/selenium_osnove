package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EksplicitnoUvod {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demoqa.com/dynamic-properties");
        WebElement btn = driver.findElement(By.id("enableAfter"));
        System.out.println("Kreni da cekas uslov");
       // wait.until(ExpectedConditions.elementToBeClickable(By.id("enableAfter")));
        wait.withMessage("Dugme nije postalo klikabilno")
            .pollingEvery(Duration.ofMillis(200))
            .withTimeout(Duration.ofSeconds(7))
            .until(ExpectedConditions.elementToBeClickable(btn));

        System.out.println("Kraj");






    }
}
