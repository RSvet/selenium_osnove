package d02_10_2023;

import d02_10_2023.Helper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws IOException {
        /*
        3. Napisati program koji:
●	Ucitava stranicu https://demoqa.com/broken
●	Hvata oba linka sa stranice i
●	Za svaki link proverava status da je veci ili jednak od 200 i manji od 400
●	Koristan link za citanje status koda nekog url-a

    ZADATAK 5 IZ p02_10_2023
         */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://demoqa.com/broken");

        List<WebElement> allLinks = driver.findElements(By.cssSelector(".playgound-body a"));

        for (int i = 0; i < allLinks.size(); i++) {
            int responseCode =  Helper.getHTTPResponseStatusCode(allLinks.get(i).getAttribute("href"));
            Helper.printStatusMessage(responseCode);
        }
    }
}
