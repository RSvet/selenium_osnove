package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class UvodTrazenjePrograma {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://s.bootsnipp.com/iframe/oV91g");
        WebElement pager = driver.findElement(By.cssSelector("#myPager"));
        pager.findElement(By.cssSelector("a"));

        List<WebElement> links = pager.findElements(By.cssSelector("a.page_link"));
    }
}
