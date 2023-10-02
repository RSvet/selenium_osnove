package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import p02_10_2023.Helper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) throws IOException {
        /*
        Napisati program koji:
        Ucitava stranicu https://demoqa.com/broken
        Hvata oba linka sa stranice i
        Za svaki link proverava status da je veci ili jednak od 200 i manji od 400
        Koristan link za citanje status koda nekog url-a
         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://demoqa.com/broken");

        List<WebElement> allLinks = driver.findElements(By.cssSelector(".playgound-body a"));

        for (int i = 0; i < allLinks.size(); i++) {
          int responseCode =  Helper.getHTTPResponseStatusCode(allLinks.get(i).getAttribute("href"));

           if(responseCode>=200 && responseCode<400)
               System.out.println((i+1)+". Status je veci/jednak od 200 i manji od 400");
           else if (responseCode<200)
               System.out.println((i+1)+". Status je manji od 200");
           else System.out.println((i+1)+". Status je veci/jednak od 400");
        }

    }
}
