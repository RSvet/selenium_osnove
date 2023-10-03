package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak4 {
    public static void main(String[] args) throws IOException {
        /*
        Napisati program koji:
●	Ucitava stranicu https://itbootcamp.rs/
●	Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
●	Cita sve linkove slika iz slajdera
●	Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a manji od 300, skida i smesta u folder itbootcamp_slider u okviru projekta
●	Azurirajte gitignore da ignorise itbootcamp_slider folder
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://itbootcamp.rs/");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.className("slider_bkgd")))
                .perform();

        List<WebElement> allCarouselImages = driver.findElements(By.cssSelector("div.owl-item img"));

        for (int i = 0; i < allCarouselImages.size(); i++) {
            String imgSource = allCarouselImages.get(i).getAttribute("src");
            int statusCode = Helper.getHTTPResponseStatusCode(imgSource);
            if(statusCode>=200 && statusCode<300){
                Helper.downloadUsingStream(imgSource, "itbootcamp_slider/img-"+i+".png");
            }
        }

    }
}
