package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UvodUTrazenje {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

//      driver.get("https://cms.demo.katalon.com/");
        driver.get("https://cms.demo.katalon.com/my-account/");
        // //div[@id='primary-menu']/ul/li[1]/a
        // #primary-menu > ul > li:nth-child(1) > a

  //     WebElement cartLink = driver.findElement(By.xpath("//div[@id='primary-menu']/ul/li[1]/a"));
//        WebElement cartLink = driver.findElement(By.cssSelector("#primary-menu > ul > li:nth-child(1) > a"));
//       cartLink.click();

//        driver.findElement(By.cssSelector("#primary-menu > ul > li:nth-child(1) > a")).click();


        driver.findElement(By.cssSelector("#username")).sendKeys("username");
        driver.quit();

    }

}
