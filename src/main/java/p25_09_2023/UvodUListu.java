package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class UvodUListu {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://cms.demo.katalon.com/my-account");



        List<WebElement> links = driver.findElements(By.cssSelector("li.page_item > a"));

        for (int i = 0; i < links.size(); i++) {
            links.get(i).clear();
        }

        driver.quit();
    }
}
