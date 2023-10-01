package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException {
        /*
        4.Zadatak
        Napisati program koji
        ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
        Klik Primary dugme
        Ceka da se pojavi toasts u gornjem desnom uglu
        Ispisuje da se element pojavio
        Ceka da se izgubi toasts u gornjem desnom uglu
        Ispisuje da se elment izgubio
        Klik na primary dugme
        Ceka da se pojavi toasts u gornjem desnom uglu
        Ispisuje da se element pojavio
        Klik na x dugme iz toasts-a
        Ceka da se element izgubi
        Ispisuje da se element izgubio
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        driver.findElement(By.id("basic-primary-trigger")).click();
        WebElement el = driver.findElement(By.cssSelector("div#basic-primary-example"));
        wait.until(ExpectedConditions.visibilityOf(el));
        System.out.println("Element sa bootstrap klasom primary se pojavio");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#basic-primary-example")));
        System.out.println("Element se izgubio");

        driver.findElement(By.id("basic-primary-trigger")).click();
        wait.until(ExpectedConditions.visibilityOf(el));
        System.out.println("Element sa bootstrap klasom primary se pojavio");

        driver.findElement(By.cssSelector("div#basic-primary-example .btn-close")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#basic-primary-example")));
        System.out.println("Element se izgubio");

    }
}
