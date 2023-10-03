package d02_10_2023;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {
        /*
        1.Zadatak
Napisati program koji:
●	Podesava:
○	implicitno cekanje za trazenje elemenata od 10s
○	implicitno cekanje za ucitavanje stranice od 10s
○	eksplicitno cekanje podeseno na 10s
●	Podaci:
○	Potrebno je u projektu ukljuciti 4 slike:
■	front.jpg
■	left.jpg
■	right.jpg
■	back.jpg
●	Koraci:
○	Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
○	Maksimizuje prozor
○	Klik na edit ikonicu
○	Klik na delete iz iskacuceg dijaloga
○	Klik na Add Image dugme
○	Sacekajte da se pojavi desni meni
○	Uploadujte front.jpg sliku
○	Sacekajte da je ispod uploada slike, broj slika 1.
○	Klik na sliku
○	Klik na Done dugme
○	Sacekajte 2s
○	Klik na Add Image dugme
○	Sacekajte da se pojavi desni meni
○	Uploadujte right.jpg sliku
○	Sacekajte da je ispod uploada slike, broj slika 2.
○	Klik na sliku
○	Klik na Done dugme
○	Sacekajte 2s
○	Klik na Add Image dugme
○	Sacekajte da se pojavi desni meni
○	Uploadujte back.jpg sliku
○	Sacekajte da je ispod uploada slike, broj slika 3.
○	Klik na sliku
○	Klik na Done dugme
○	Sacekajte 2s
○	Klik na Add Image dugme
○	Sacekajte da se pojavi desni meni
○	Uploadujte back.jpg sliku
○	Sacekajte da je ispod uploada slike, broj slika 3.
○	Klik na sliku
○	Klik na Done dugme
○	Sacekajte 2s
○	Sacekajte da Next dugme bude klikljivo
○	Klik na Next dugme
○	Unesite tekst
○	Klik na Next
○	Klik na Preview
○	Klik na Add to cart
○	Sacekajte 5s
○	Quit
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<String> imgPaths =
                new ArrayList<>(Arrays.asList("box_images/5544590_11zon.jpg", "box_images/puppies_11zon.jpg", "box_images/puppy.jpg", "box_images/puppy cat_11zon.jpg"));

        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.manage().window().maximize();
        driver.findElement(By.id("active-face")).click();
        driver.findElement(By.id("image-option-remove")).click();

        for (int i = 0; i < imgPaths.size(); i++) {
            driver.findElement(By.id("active-face")).click();
            wait
                    .withMessage("Upload option didn't appear!")
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")))
                    .sendKeys(new File(imgPaths.get(i)).getAbsolutePath());
            wait
                    .withMessage("Image didn't upload!")
                    .until(ExpectedConditions.numberOfElementsToBe(By.className("sc-brKeYL"), i+1));
            driver.findElement(By.id("image-option-0")).click();
            wait
                    .withMessage("Can't upload, there is no submit button!")
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();

            Thread.sleep(2000);
        }

        wait
                .withMessage("Next button is not clickable")
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sc-ejDCVS.bObSCL"))).click();

        driver.findElement(By.tagName("textarea")).click();
        driver.findElement(By.tagName("textarea")).sendKeys("Svetlana");
        Thread.sleep(1000);

        driver.findElement(By.id("next-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("next-button")).click();

        // rotate element for display purpose
        Actions actions = new Actions(driver);
        WebElement main = driver.findElement(By.tagName("main"));
        actions.moveToElement(main);
        actions.clickAndHold()
                .moveByOffset(-400,300)
                .moveByOffset(400,100);
        actions.release().perform();
        Thread.sleep(1500);

        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();

        Thread.sleep(5000);
        driver.quit();















    }
}
