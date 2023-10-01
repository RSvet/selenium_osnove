package d29_09_2023;

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

import java.time.Duration;
import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        /*
        2.	Zadatak (ZA VEZBANJE)
        ●	Modifikovati zadatak 1 tako da se skrol vrsi u vise iteracija, npr u 5
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Select delay = new Select(driver.findElement(By.id("delay-select")));
        delay.selectByValue("2000");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.xpath("//div[text()='C']")))
                .perform();

        for (int i = 0; i < 5; i++) {
            new Actions(driver)
                    .scrollToElement(driver.findElement(By.id("infinite-scroll-button")))
                    .perform();

            List<WebElement> elements = driver.findElements(By.cssSelector("div.item"));

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#infinite-scroll-button"))).click();

            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.item"), elements.size()+3));

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@disabled]")));

            elements=driver.findElements(By.cssSelector("div.item"));

            if(i==4){
                new Actions(driver)
                        .scrollToElement(driver.findElement(By.id("infinite-scroll-button")))
                        .perform();
            }
            else{
                new Actions(driver).scrollToElement(elements.get(elements.size()-2)).perform();
            }

        }
        System.out.println("Kraj");



    }
}
