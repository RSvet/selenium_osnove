package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://cms.demo.katalon.com/");
//        driver.findElement(By.linkText("CART")).click();

//        driver.close();
//        driver.navigate().to("https://cms.demo.katalon.com/");
//        driver.navigate().to("https://google.com/");
//        driver.navigate().back();
//        driver.navigate().forward();
 //       driver.findElement(By.cssSelector("input.search-field")).sendKeys("Flying Ninja");
//        driver.findElement(By.name("s")); vraca prvi element sa tim name-om
//        driver.findElement(By.className("search-submit"));
 //       driver.findElement(By.xpath("//button[@type='submit']")).click();
 //       Thread.sleep(5000);



  //      driver.quit();
    }
}
