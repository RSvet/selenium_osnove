package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        ●	Ucitati stranicu http://seleniumdemo.com/?post_type=product
        ●	Klik na search dugme u gornjem desnom uglu
        ●	Cekati da forma za pretragu bude vidljiva
        ●	Uneti sledeci tekst za pretragu BDD Cucumber i ENTER
        ●	Dohvatiti prvi rezultat pretrage i proveriti da li u nazivu sadrzi tekst koji je unet za pretragu.
        Ispisati odgovarajuce poruke u terminalu
         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("http://seleniumdemo.com/?post_type=product");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector(".mobile-navbar__wrapper a.search-toggle_btn")).click();
        WebElement searchInput = driver.findElement(By.cssSelector(".mobile-navbar__wrapper input[title = 'Search …']"));
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        String searchText = "BDD Cucumber";
        searchInput.sendKeys(searchText);
        searchInput.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("article:first-child .czr-title")));
        if(driver.findElement(By.cssSelector("article:first-child .czr-title")).getText().contains(searchText)){
            System.out.println("Searched text is present in the result title");
        }
        else System.out.println("Searched text is not present");

        Thread.sleep(1000);
        driver.quit();

    }
}
